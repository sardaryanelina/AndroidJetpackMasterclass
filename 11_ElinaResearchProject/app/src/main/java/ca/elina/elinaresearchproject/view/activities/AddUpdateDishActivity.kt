package ca.elina.elinaresearchproject.view.activities

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import ca.elina.elinaresearchproject.R
import ca.elina.elinaresearchproject.databinding.ActivityAddUpdateDishBinding
import ca.elina.elinaresearchproject.databinding.DialogCustomImageSelectionBinding
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.karumi.dexter.listener.single.PermissionListener

/**
 * A screen where we can add and update the dishes.
 */

class AddUpdateDishActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mBinding: ActivityAddUpdateDishBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityAddUpdateDishBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        setupActionBar()

        mBinding.ivAddDishImage.setOnClickListener(this@AddUpdateDishActivity)
    }

    override fun onClick(v: View) {

        when (v.id) {

            R.id.iv_add_dish_image -> {

                customImageSelectionDialog()
                return
            }
        }
    }

    // TODO Step 4: Override the onActivityResult method.
    // START
    /**
     * Receive the result from a previous call to
     * {@link #startActivityForResult(Intent, int)}.  This follows the
     * related Activity API as described there in
     * {@link Activity#onActivityResult(int, int, Intent)}.
     *
     * @param requestCode The integer request code originally supplied to
     *                    startActivityForResult(), allowing you to identify who this
     *                    result came from.
     * @param resultCode The integer result code returned by the child activity
     *                   through its setResult().
     * @param data An Intent, which can return result data to the caller
     *               (various data can be attached to Intent "extras").
     */
    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CAMERA) {

                // TODO Step 5: Get Image from Camera and set it to the ImageView
                // START
                val thumbnail: Bitmap = data!!.extras!!.get("data") as Bitmap // Bitmap from camera
                mBinding.ivDishImage.setImageBitmap(thumbnail) // Set to the imageView.
                // END

                // TODO Step 6: Replace the add image icon with edit icon.
                // START
                // Replace the add icon with edit icon once the image is selected.
                mBinding.ivAddDishImage.setImageDrawable(
                    ContextCompat.getDrawable(
                        this@AddUpdateDishActivity,
                        R.drawable.ic_vector_edit
                    )
                )
                // END
            }
        } else if (resultCode == Activity.RESULT_CANCELED) {
            Log.e("Cancelled", "Cancelled")
        }
    }
    // END

    /**
     * A function for ActionBar setup.
     */
    private fun setupActionBar() {
        setSupportActionBar(mBinding.toolbarAddDishActivity)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)

        mBinding.toolbarAddDishActivity.setNavigationOnClickListener { onBackPressed() }
    }


    /**
     * A function to launch the custom image selection dialog.
     */
    private fun customImageSelectionDialog() {
        val dialog = Dialog(this@AddUpdateDishActivity)

        val binding: DialogCustomImageSelectionBinding =
            DialogCustomImageSelectionBinding.inflate(layoutInflater)

        /*Set the screen content from a layout resource.
        The resource will be inflated, adding all top-level views to the screen.*/
        dialog.setContentView(binding.root)

        binding.tvCamera.setOnClickListener {
            // Let ask for the permission while selecting the image from camera using Dexter Library.
            Dexter.withContext(this@AddUpdateDishActivity)
                .withPermissions(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    // This is not required anymore in the latest API levels.
                    // If you read this code on your older device or on a device that uses
                    // an older Android version, this will work.
                    // TODO Step 7: We comment or delete the line below, because it was for older devices below API 30
                 //   Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA
                )
                .withListener(object : MultiplePermissionsListener {
                    override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                        // Here after all the permission are granted launch the CAMERA to capture an image.
                        // TODO step 10: check that report is not null then implement the code
                        report?.let{
                            if (report.areAllPermissionsGranted()) {

                                // TODO Step 3: Start camera using the Image capture action. Get the result in the onActivityResult method as we are using startActivityForResult.
                                // START
                                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE) // allows us to capture the image
                                startActivityForResult(intent, CAMERA)
                                // END
                            }
                        }
                    }

                    override fun onPermissionRationaleShouldBeShown(
                        permissions: MutableList<PermissionRequest>?,
                        token: PermissionToken?
                    ) {
                        showRationalDialogForPermissions()
                    }
                    // If you want to receive permission listener callbacks on the same thread that fired the permission request,
                    // you just need to call onSameThread before checking for permissions:
                }).onSameThread()
                .check()

            dialog.dismiss()
        }

        binding.tvGallery.setOnClickListener {
            //  Ask for the permission while selecting the image from Gallery using Dexter Library.
            // TODO step 8: Change multiple permissions listener into a single permission

            // We deleted Manifest.permission.WRITE_EXTERNAL_STORAGE
            // because we are only listening for one permission.
            Dexter.withContext(this@AddUpdateDishActivity)
                .withPermission(
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
                .withListener(object : PermissionListener {
                    // TODO step 9: by deleting Manifest.permission.WRITE_EXTERNAL_STORAGE and overloading methods
                    //   for single permission (onPermissionGranted, onPermissionDenied, onPermissionRationaleShouldBeShown)
                    override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                        Toast.makeText(
                            this@AddUpdateDishActivity,
                            "You have the Gallery permission now to select image.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    override fun onPermissionDenied(response: PermissionDeniedResponse) {
                        Toast.makeText(
                            this@AddUpdateDishActivity,
                            "You have denied the storage permission to select image.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    override fun onPermissionRationaleShouldBeShown(
                        permission: PermissionRequest,
                        token: PermissionToken
                    ) {
                        showRationalDialogForPermissions()
                    }
                    // If you want to receive permission listener callbacks on the same thread that fired the permission request,
                    // you just need to call onSameThread before checking for permissions:
                }).onSameThread()
                .check()
            dialog.dismiss()
        }

        //Start the dialog and display it on screen.
        dialog.show()
    }


    /**
     * A function used to show the alert dialog when the permissions are denied and need to allow it from settings app info.
     */
    private fun showRationalDialogForPermissions() {
        AlertDialog.Builder(this)
            .setMessage("It Looks like you have turned off permissions required for this feature. It can be enabled under Application Settings")
            .setPositiveButton(
                "GO TO SETTINGS"
            ) { _, _ ->
                try {
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri = Uri.fromParts("package", packageName, null)
                    intent.data = uri
                    startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    e.printStackTrace()
                }
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }.show()
    }


    // TODO Step 2: Define the Companion Object to define the constants used in the class. We will define the constant for camera.
    // START
    companion object {
        private const val CAMERA = 1
    }
    // END
}
