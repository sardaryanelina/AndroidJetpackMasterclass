package ca.elina.elinaresearchproject.view.fragments

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import ca.elina.elinaresearchproject.databinding.FragmentRandomDishBinding
import ca.elina.elinaresearchproject.viewmodel.RandomDishViewModel
import androidx.lifecycle.Observer
import ca.elina.elinaresearchproject.R
import ca.elina.elinaresearchproject.application.FavDishApplication
import ca.elina.elinaresearchproject.model.entities.FavDish
import ca.elina.elinaresearchproject.model.entities.RandomDish
import ca.elina.elinaresearchproject.utils.Constants
import ca.elina.elinaresearchproject.viewmodel.FavDishViewModel
import ca.elina.elinaresearchproject.viewmodel.FavDishViewModelFactory
import com.bumptech.glide.Glide

// NotificationsFragment refactored to RandomDishFragment.
class RandomDishFragment : Fragment() {

    private var mBinding: FragmentRandomDishBinding? = null

    private lateinit var mRandomDishViewModel: RandomDishViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentRandomDishBinding.inflate(inflater, container, false)
        return mBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the ViewModel variable.
        mRandomDishViewModel =
            ViewModelProvider(this).get(RandomDishViewModel::class.java)

        mRandomDishViewModel.getRandomDishFromAPI()

        randomDishViewModelObserver()
    }

    /**
     * A function to get the data in the observer after the API is triggered.
     */
    private fun randomDishViewModelObserver() {

        mRandomDishViewModel.randomDishResponse.observe(
            viewLifecycleOwner,
            Observer { randomDishResponse ->
                randomDishResponse?.let {
                    Log.i("Random Dish Response", "${randomDishResponse.recipes[0]}")
                    // TODO Step 2: Call the function to populate the response in the UI.
                    // START
                    setRandomDishResponseInUI(randomDishResponse.recipes[0])
                    // END
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

    // TODO Step 1: Create a method to populate the API response in the UI.
    // START
    /**
     * A method to populate the API response in the UI.
     *
     * @param recipe - Data model class of the API response with filled data.
     */
    private fun setRandomDishResponseInUI(recipe: RandomDish.Recipe) {

        // Load the dish image in the ImageView.
        Glide.with(requireActivity())
            .load(recipe.image)
            .centerCrop()
            .into(mBinding!!.ivDishImage)

        mBinding!!.tvTitle.text = recipe.title

        // Default Dish Type
        var dishType: String = "other"

        if (recipe.dishTypes.isNotEmpty()) {
            dishType = recipe.dishTypes[0]
            mBinding!!.tvType.text = dishType
        }

        // There is not category params present in the response so we will define it as Other.
        mBinding!!.tvCategory.text = "Other"

        var ingredients = ""
        for (value in recipe.extendedIngredients) {

            if (ingredients.isEmpty()) {
                ingredients = value.original
            } else {
                ingredients = ingredients + ", \n" + value.original
            }
        }

        mBinding!!.tvIngredients.text = ingredients

        // The instruction or you can say the Cooking direction text is in the HTML format so we will you the fromHtml to populate it in the TextView.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mBinding!!.tvCookingDirection.text = Html.fromHtml(
                recipe.instructions,
                Html.FROM_HTML_MODE_COMPACT
            )
        } else {
            @Suppress("DEPRECATION")
            mBinding!!.tvCookingDirection.text = Html.fromHtml(recipe.instructions)
        }

        mBinding!!.tvCookingTime.text =
            resources.getString(
                R.string.lbl_estimate_cooking_time,
                recipe.readyInMinutes.toString()
            )

        // TODO step 6: Assign the click event to the Favorite Button and add the dish details to the local database if user click on it.
        // START
        mBinding!!.ivFavoriteDish.setOnClickListener {

            // TODO Step 7: Create a instance of FavDish data model class and fill it with required information from the API response.
            // START
            val randomDishDetails = FavDish(
                recipe.image,
                Constants.DISH_IMAGE_SOURCE_ONLINE,
                recipe.title,
                dishType,
                "Other",
                ingredients,
                recipe.readyInMinutes.toString(),
                recipe.instructions,
                true
            )
            // END

            // TODO Step 8: Create an instance of FavDishViewModel class and call insert function and pass the required details.
            // START
            val mFavDishViewModel: FavDishViewModel by viewModels {
                FavDishViewModelFactory((requireActivity().application as FavDishApplication).repository)
            }

            mFavDishViewModel.insert(randomDishDetails)
            // END

            // TODO Step 9: Once the dish is inserted you can acknowledge user by Toast message as below and also update the favorite image by selected.
            // START
            mBinding!!.ivFavoriteDish.setImageDrawable(
                ContextCompat.getDrawable(
                    requireActivity(),
                    R.drawable.ic_favorite_selected
                )
            )

            Toast.makeText(
                requireActivity(),
                resources.getString(R.string.msg_added_to_favorites),
                Toast.LENGTH_SHORT
            ).show()
            // END
        }
        // END
    }
    // END

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }
}