package ca.elina.elinaresearchproject.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ca.elina.elinaresearchproject.databinding.FragmentRandomDishBinding
import ca.elina.elinaresearchproject.viewmodel.RandomDishViewModel
import androidx.lifecycle.Observer

// NotificationsFragment refactored to RandomDishFragment.
class RandomDishFragment : Fragment() {

    private var mBinding: FragmentRandomDishBinding? = null

    // TODO Step 1: Create an instance of the ViewModel Class
    // START
    private lateinit var mRandomDishViewModel: RandomDishViewModel
    // END

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentRandomDishBinding.inflate(inflater, container, false)
        return mBinding!!.root
    }

    // TODO Step 2: Override the onViewCreated method and Initialize the ViewModel variable.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the ViewModel variable.
        mRandomDishViewModel =
            ViewModelProvider(this).get(RandomDishViewModel::class.java)

        // TODO Step 3: Call the function to get the response from API.
        // START
        mRandomDishViewModel.getRandomDishFromAPI()
        // END

        // TODO Step 5: Call the observer function.
        // START
        randomDishViewModelObserver()
        // END
    }

    // TODO Step 4: Create a function to get the data in the observer after the API is triggered.
    // START
    /**
     * A function to get the data in the observer after the API is triggered.
     */
    private fun randomDishViewModelObserver() {

        mRandomDishViewModel.randomDishResponse.observe(
            viewLifecycleOwner,
            Observer { randomDishResponse ->
                randomDishResponse?.let {
                    Log.i("Random Dish Response", "$randomDishResponse.recipes[0]")
                }
            })

        mRandomDishViewModel.randomDishLoadingError.observe(
            viewLifecycleOwner,
            Observer { dataError ->
                dataError?.let {
                    Log.i("Random Dish API Error", "$dataError")
                }
            })

        mRandomDishViewModel.loadRandomDish.observe(viewLifecycleOwner, Observer { loadRandomDish ->
            loadRandomDish?.let {
                Log.i("Random Dish Loading", "$loadRandomDish")
            }
        })
    }
    // END

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }
}