package ca.elina.a1_favdish

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ca.elina.a1_favdish.databinding.ActivitySplashBinding

// TODO Step 1: Create a new activity as Splash Screen.
// START
/**
 * A Splash Screen
 */
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO Step 5: Access the XML layout file using the ViewBiding.
        // START
        val splashBinding: ActivitySplashBinding = ActivitySplashBinding.inflate(layoutInflater)
        // END

        // TODO Step 6: Update the content view using the ViewBinding
        // START
        setContentView(splashBinding.root)
        // END

        // TODO Step 7: You can access the TextView without findViewById or Kotlin extensions using splashBinding variable and perform the operations that you want.
        // START
         splashBinding.tvAppName.text = "Hello world"
        // END
    }
}
// END