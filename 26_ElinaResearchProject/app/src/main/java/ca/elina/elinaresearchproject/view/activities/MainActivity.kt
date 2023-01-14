package ca.elina.elinaresearchproject.view.activities

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import ca.elina.elinaresearchproject.R
import ca.elina.elinaresearchproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // TODO Step 1: Create a global variable for ViewBinding.
    // START
    private lateinit var mBinding: ActivityMainBinding
    // END

    // TODO Step 2: Make the navController variable as global variable.
    // START
    private lateinit var mNavController: NavController
    // END

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO Step 3: Initialize the mBinding variable.
        // START
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        // END

        // TODO Step 4: Remove this line of code
        // START
        // val navView: BottomNavigationView = findViewById(R.id.nav_view)
        // END

        mNavController = findNavController(R.id.nav_host_fragment) // replace mNavController
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_all_dishes,
                R.id.navigation_favorite_dishes,
                R.id.navigation_random_dish
            )
        )
        setupActionBarWithNavController(mNavController, appBarConfiguration)
        mBinding.navView.setupWithNavController(mNavController) // use ViewBinding
    }

    // TODO Step 5: Override the onSupportNavigateUp method.
    // START
    override fun onSupportNavigateUp(): Boolean {

        // TODO Step 6: Add the navigate up code and pass the required params. This will navigate the user from DishDetailsFragment to AllDishesFragment when user clicks on the home back button.
        // START
        return NavigationUI.navigateUp(mNavController, null) // user can navigate back
        // END
    }
    // END

    // TODO Step 7: Create a function to hide the BottomNavigationView with animation.
    // START
    fun hideBottomNavigationView() {
        mBinding.navView.clearAnimation() // in case there are animations running, we get rid of them
        mBinding.navView.animate().translationY(mBinding.navView.height.toFloat()).duration = 300
    }
    // END

    // TODO Step 8: Create a function to show the BottomNavigationView with Animation.
    // START
    fun showBottomNavigationView() {
        mBinding.navView.clearAnimation()
        mBinding.navView.animate().translationY(0f).duration = 300
    }
    // END
}