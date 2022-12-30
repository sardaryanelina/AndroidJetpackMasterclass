package com.tutorials.eu.glidedemo

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


class MainActivity : AppCompatActivity() {

    // TODO Step 1: A Random image from google to load it using Glide.
    private val image = "https://cdn.pixabay.com/photo/2018/05/03/21/49/android-3372580_960_720.png"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO Step 4: Create an instance of the ImageView's using FindViewById for now.
        // START
        val imageOne = findViewById<ImageView>(R.id.image_one)
        val imageTwo = findViewById<ImageView>(R.id.image_two)
        val imageThree = findViewById<ImageView>(R.id.image_three)
        // END

        // TODO Step 5: Load the image to the ImageView with simple options.
        // START
        Glide.with(this)
                .load(image) // A new request builder for loading a Drawable using the given model.
                .into(imageOne) // Sets the ImageView the resource will be loaded into
        // END

        // TODO Step 6: Load the image to the ImageView with some more params.
        // START
        Glide.with(this)
                .load(image)
                .fitCenter()
                .circleCrop() // Applies CircleCrop to all default types and throws an exception if asked to transform an unknown type.
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.img_placeholder)
                .into(imageTwo)
        // END

        // TODO Step 7: Load the image to the ImageView with custom size defined at runtime.
        // START
        Glide.with(this)
                // To check the error you can change the link by adding some char
                .load("https://cdn.pixabay.com/photo/2018/05/03/21/49/android-3372580_960_720.png")
                .override(300, 400)
                .centerCrop() // scale to fill the ImageView and crop any extra
                .error(R.drawable.img_placeholder)
                .into(imageThree)
        // END
    }
}