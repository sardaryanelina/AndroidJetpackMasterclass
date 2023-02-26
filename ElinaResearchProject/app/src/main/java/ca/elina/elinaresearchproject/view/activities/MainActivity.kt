package ca.elina.elinaresearchproject.view.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.work.*
import ca.elina.elinaresearchproject.R
import ca.elina.elinaresearchproject.databinding.ActivityMainBinding
import ca.elina.elinaresearchproject.model.notification.NotifyWorker
import ca.elina.elinaresearchproject.utils.Constants
import java.util.concurrent.TimeUnit
/**
 * Taken from Udemy Course Tutorial
 * https://www.udemy.com/course/android-jetpack-masterclass
 * Created By Elina Sardaryan
 * College - John Abbott
 * MAP-06
 * December 2022 - January 2023 */
class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding

    private lateinit var mNavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

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

        // Handle the Notification when user clicks on it.
        if (intent.hasExtra(Constants.NOTIFICATION_ID)) {
            val notificationId = intent.getIntExtra(Constants.NOTIFICATION_ID, 0)
            Log.i("Notification Id", "$notificationId")

            // The Random Dish Fragment is selected when user is redirect in the app via Notification.
            mBinding.navView.selectedItemId = R.id.navigation_random_dish
        }

        startWork()
    }

    // Override the onSupportNavigateUp method.
    override fun onSupportNavigateUp(): Boolean {

        // navigateUp with required parameters.
        // This will navigate the user from DishDetailsFragment to AllDishesFragment
        // when user clicks on the home back button.
        return NavigationUI.navigateUp(mNavController, null) // user can navigate back
    }

    // Visibility parameter to NavigationBottomView in both the hide and show functions.
    /**
     * A function to hide the BottomNavigationView with animation.
     */
    // A function to hide the BottomNavigationView with animation.
    fun hideBottomNavigationView() {
        mBinding.navView.clearAnimation()
        mBinding.navView.animate().translationY(mBinding.navView.height.toFloat()).duration = 300
        mBinding.navView.visibility = View.GONE
    }

    /**
     * A function to show the BottomNavigationView with Animation.
     */
    fun showBottomNavigationView() {
        mBinding.navView.clearAnimation()
        mBinding.navView.animate().translationY(0f).duration = 300
        mBinding.navView.visibility = View.VISIBLE
    }

    // A function to setup the Constraints as below.
    /**
     * Constraints ensure that work is deferred until optimal conditions are met.
     *
     * A specification of the requirements that need to be met before a WorkRequest can run.
     * By default, WorkRequests do not have any requirements and can run immediately.
     * By adding requirements, you can make sure that work only runs in certain situations
     * - for example, when you have an unmetered network and are charging.
     */
    // For more details visit the link https://medium.com/androiddevelopers/introducing-workmanager-2083bcfc4712
    private fun createConstraints() = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.NOT_REQUIRED)  // if connected to WIFI
        .setRequiresCharging(false)
        .setRequiresBatteryNotLow(true)                 // if the battery is not low
        .build()

    // A function to setup the PeriodicWorkRequestBuilder as below.
    /**
     * You can use any of the work request builder that are available to use.
     * We will you the PeriodicWorkRequestBuilder as we want to execute the code periodically.
     *
     * The minimum time you can set is 15 minutes. You can check the same on the below link.
     * https://developer.android.com/reference/androidx/work/PeriodicWorkRequest
     *
     * You can also set the TimeUnit as per your requirement. for example SECONDS, MINUTES, or HOURS.
     */
    // setting period to 15 Minutes
    private fun createWorkRequest() = PeriodicWorkRequestBuilder<NotifyWorker>(15, TimeUnit.MINUTES)
        .setConstraints(createConstraints())
        .build()

    // A function to startWork and pass the required params as below.
    private fun startWork() {

        /* enqueue a work, ExistingPeriodicWorkPolicy.KEEP means that if this work already exists, it will be kept
        if the value is ExistingPeriodicWorkPolicy.REPLACE, then the work will be replaced */
        WorkManager.getInstance(this)
            .enqueueUniquePeriodicWork(
                "FavDish Notify Work",
                ExistingPeriodicWorkPolicy.KEEP,
                createWorkRequest() // Pass the WorkRequest
            )
    }
}