package ca.elina.elinaresearchproject.view.activities

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import ca.elina.elinaresearchproject.R
import ca.elina.elinaresearchproject.databinding.ActivityAddUpdateDishBinding
import ca.elina.elinaresearchproject.databinding.DialogCustomImageSelectionBinding

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

        mBinding.ivAddDishImage.setOnClickListener(this)
    }

    override fun onClick(v: View) {

        // Perform the action when user clicks on the addDishImage and show Toast message for now.
        when (v.id) {
            R.id.iv_add_dish_image -> {
//                Toast.makeText(
//                    this,
//                    "You have clicked on the ImageView.",
//                    Toast.LENGTH_SHORT
//                ).show()
                // TODO Step 6: Replace the Toast Message with the custom dialog.
                // START
                customImageSelectionDialog()
                // END
                return
            }
        }
    }

    /**
     * A function for ActionBar setup.
     */
    private fun setupActionBar() {
        // action bar in xml -> id is toolbar_add_dish_activity
        // we are assigning this bar using default method setSupportActionBar
        setSupportActionBar(mBinding.toolbarAddDishActivity)

        // this will allow us to have back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)

        mBinding.toolbarAddDishActivity.setNavigationOnClickListener { onBackPressed() }
    }

    // TODO Step 5: Create a function to launch the custom dialog.
    // START
    /**
     * A function to launch the custom image selection dialog.
     */
    private fun customImageSelectionDialog() {
        val dialog = Dialog(this)

        val binding: DialogCustomImageSelectionBinding = DialogCustomImageSelectionBinding.inflate(layoutInflater)

        /*Set the screen content from a layout resource.
        The resource will be inflated, adding all top-level views to the screen.*/
        dialog.setContentView(binding.root)

        // TODO Step 7: Assign the click for Camera and Gallery. Show the Toast message for now.
        // START
        binding.tvCamera.setOnClickListener {
            Toast.makeText(this, "You have clicked on the Camera.", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        binding.tvGallery.setOnClickListener {
            Toast.makeText(this, "You have clicked on the Gallery.", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        // END

        //Start the dialog and display it on screen.
        dialog.show()
    }
    // END
}
