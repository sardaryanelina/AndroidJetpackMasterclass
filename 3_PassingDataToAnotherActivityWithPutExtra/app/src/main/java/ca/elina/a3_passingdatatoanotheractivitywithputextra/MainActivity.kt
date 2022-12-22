package ca.elina.a3_passingdatatoanotheractivitywithputextra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO Step 1: Access the button and add the click event to the Button.
        // START
        val btnSubmit = findViewById<Button>(R.id.btn_submit)
        btnSubmit.setOnClickListener {

            // TODO Step 4: Launch the Another Activity and pass the data using putExtra.
            // START
            val intent = Intent(this@MainActivity, AnotherActivity::class.java).apply {
                putExtra("key1", "Value1")
                putExtra("key2", 1.28)
                // You can add as much as params you want.
            }
            startActivity(intent)
        }
        // END
    }
}