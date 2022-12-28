package ca.elina.elinaresearchproject.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ca.elina.elinaresearchproject.R
// TODO Step 1: Create a new activity as AddUpdateDishActivity.
// START
/**
 * A screen where we can add and update the dishes.
 */
class AddUpdateDishActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_update_dish)
    }
}
// END