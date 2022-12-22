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

            // TODO Step 2: Launch the Another Activity
            // START
            val intent = Intent(this, AnotherActivity::class.java)
            startActivity(intent)
        }
        // END
    }
}