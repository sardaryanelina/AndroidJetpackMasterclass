package ca.elina.elinaresearchproject.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ca.elina.elinaresearchproject.R
import ca.elina.elinaresearchproject.application.FavDishApplication
import ca.elina.elinaresearchproject.viewmodel.FavDishViewModel
import ca.elina.elinaresearchproject.viewmodel.FavDishViewModelFactory

// DashboardFragment refactored to FavoriteFragment.
class FavoriteDishesFragment : Fragment() {

    // TODO Step  5: Create an instance of ViewModel to access the methods that are necessary to populate the UI.
    // START
    /**
     * To create the ViewModel we used the viewModels delegate, passing in an instance of our FavDishViewModelFactory.
     * This is constructed based on the repository retrieved from the FavDishApplication.
     */
    private val mFavDishViewModel: FavDishViewModel by viewModels {
        FavDishViewModelFactory((requireActivity().application as FavDishApplication).repository)
    }

    // END
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_favorite_dishes, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        textView.text = "Favorite Dishes Fragment"
        return root
    }

    // TODO Step 4: Override the onViewCreated method.
    // START
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO Step 6: Add an observer to get the list of updated favorite dishes.
        // START
        /**
         * Add an observer on the LiveData returned by getFavoriteDishesList.
         * The onChanged() method fires when the observed data changes and the activity is in the foreground.
         */
        mFavDishViewModel.favoriteDishes.observe(viewLifecycleOwner) { dishes ->
            dishes.let {
                if (it.isNotEmpty()) {
                    // Print the id and title in the log for now.
                    for (dish in it) {
                        Log.i("Favorite Dish", "${dish.id} :: ${dish.title}")
                    }
                } else {
                    Log.i("List of Favorite Dishes", "is empty.")
                }
            }
        }
        // END
    }
    // END
}