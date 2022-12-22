package ca.elina.a3_passingdatatoanotheractivitywithputextra

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

// TODO Step 2: Create an Another Activity to launch it via Intent and passing the data between two activities.
// START
class AnotherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_another)

        // TODO Step 5: Get the data and print it in the log.
        // START
        val keyValue1 = intent.getStringExtra("key1")
        Log.i("value 1", "$keyValue1")
        // in case of getDoubleExtra we put default value in case it is null
        val keyValue2 = intent.getDoubleExtra("key2", 1.1)
        Log.i("value 2", "$keyValue2")
        // END
    }
}
// END