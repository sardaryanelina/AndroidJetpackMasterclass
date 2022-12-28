package com.tutorials.eu.framelayoutsample

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO Step 5: Assign the click event to the add_edit image icon.
        // START
        var addImage = true
        val addEditImage = findViewById<ImageView>(R.id.iv_add_edit_image)
        addEditImage.setOnClickListener {

            if (addImage) {
                addImage = false
                addEditImage.setImageDrawable(
                    ContextCompat.getDrawable(
                        this@MainActivity,
                        R.drawable.ic_vector_edit
                    )
                )
            } else {
                addImage = true
                addEditImage.setImageDrawable(
                    ContextCompat.getDrawable(
                        this@MainActivity,
                        R.drawable.ic_add_a_photo
                    )
                )
            }
        }
        // END
    }
}