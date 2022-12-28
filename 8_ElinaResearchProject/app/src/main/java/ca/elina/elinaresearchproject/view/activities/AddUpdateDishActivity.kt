package ca.elina.elinaresearchproject.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import ca.elina.elinaresearchproject.R
import ca.elina.elinaresearchproject.databinding.ActivityAddUpdateDishBinding

/**
 * A screen where we can add and update the dishes.
 */
// TODO Step 3: Implement the View.OnClickListener.
// START
class AddUpdateDishActivity : AppCompatActivity(), View.OnClickListener {
// END

    private lateinit var mBinding: ActivityAddUpdateDishBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityAddUpdateDishBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        setupActionBar()

        // TODO Step 5: Assign the click event to the image button.
        // START
        mBinding.ivAddDishImage.setOnClickListener(this)
        // END
    }

    // TODO Step 4: Override the onclick listener method.
    // START
    override fun onClick(v: View) {

        // TODO Step 6: Perform the action when user clicks on the addDishImage and show Toast message for now.
        // START
        when (v.id) {
            R.id.iv_add_dish_image -> {
                Toast.makeText(
                    this,
                    "You have clicked on the ImageView.",
                    Toast.LENGTH_SHORT
                ).show()
                return
            }
        }
        // END
    }
    // END

    /**
     * A function for ActionBar setup.
     */
    private fun setupActionBar() {
        // action bar in xml -> id is toolbar_add_dish_activity
        // we are assigning this bar using default method setSupportActionBar
        setSupportActionBar(mBinding.toolbarAddDishActivity)

        // this will allow us to have back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // TODO Step 2: Replace the back button that we have generated.
        // this will allow us to have back button
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // START
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        // END

        mBinding.toolbarAddDishActivity.setNavigationOnClickListener { onBackPressed() }
    }
}
