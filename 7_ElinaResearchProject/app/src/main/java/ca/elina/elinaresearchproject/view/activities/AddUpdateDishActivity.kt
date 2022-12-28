package ca.elina.elinaresearchproject.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ca.elina.elinaresearchproject.databinding.ActivityAddUpdateDishBinding

/**
 * A screen where we can add and update the dishes.
 */
class AddUpdateDishActivity : AppCompatActivity() {

    // TODO Step 5: Create a global variable for layout ViewBinding
    // START
    private lateinit var mBinding: ActivityAddUpdateDishBinding
    // END

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO Step 6: Initialize the layout ViewBinding variable and set the contentView.
        // START
        mBinding = ActivityAddUpdateDishBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        // END

        // TODO Step 8: Call the method of setupActionBar
        // START
        setupActionBar()
        // END
    }

    // TODO Step 7: Create a function to setup the ActionBar
    // START
    private fun setupActionBar() {
        // action bar in xml -> id is toolbar_add_dish_activity
        // we are assigning this bar using default method setSupportActionBar
        setSupportActionBar(mBinding.toolbarAddDishActivity)

        // this will allow us to have back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // add click listener to back button
        mBinding.toolbarAddDishActivity.setNavigationOnClickListener { onBackPressed() }
    }
    // END

}
