package ca.elina.elinaresearchproject.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ca.elina.elinaresearchproject.R
import androidx.recyclerview.widget.GridLayoutManager
import ca.elina.elinaresearchproject.application.FavDishApplication
import ca.elina.elinaresearchproject.databinding.FragmentAllDishesBinding
import ca.elina.elinaresearchproject.view.activities.AddUpdateDishActivity
import ca.elina.elinaresearchproject.view.adapters.FavDishAdapter
import ca.elina.elinaresearchproject.viewmodel.FavDishViewModel
import ca.elina.elinaresearchproject.viewmodel.FavDishViewModelFactory

class AllDishesFragment : Fragment() {

    // TODO Step 5: Remove the unused code and create a global variable for the ViewBinding.
    // START
    private lateinit var mBinding: FragmentAllDishesBinding
    // END

    /**
     * To create the ViewModel we used the viewModels delegate, passing in an instance of our FavDishViewModelFactory.
     * This is constructed based on the repository retrieved from the FavDishApplication.
     */
    private val mFavDishViewModel: FavDishViewModel by viewModels {
        FavDishViewModelFactory((requireActivity().application as FavDishApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // TODO Step 6: Remove the extra code and initialize the mBinding variable.
        // START
        mBinding =
            FragmentAllDishesBinding.inflate(inflater, container, false)
        return mBinding.root
        // END
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO Step 7: Initialize the RecyclerView and bind the adapter class
        // START
        // Set the LayoutManager that this RecyclerView will use.
        mBinding.rvDishesList.layoutManager = GridLayoutManager(requireActivity(), 2)
        // Adapter class is initialized and list is passed in the param.
        val favDishAdapter = FavDishAdapter(this@AllDishesFragment)
        // adapter instance is set to the recyclerview to inflate the items.
        mBinding.rvDishesList.adapter = favDishAdapter
        // END

        /**
         * Add an observer on the LiveData returned by getAllDishesList.
         * The onChanged() method fires when the observed data changes and the activity is in the foreground.
         */
        mFavDishViewModel.allDishesList.observe(viewLifecycleOwner) { dishes ->
            dishes.let {

                // TODO Step 9: Pass the dishes list to the adapter class.
                // START
                if (it.isNotEmpty()) {

                    mBinding.rvDishesList.visibility = View.VISIBLE
                    mBinding.tvNoDishesAddedYet.visibility = View.GONE

                    favDishAdapter.dishesList(it)
                } else {

                    mBinding.rvDishesList.visibility = View.GONE
                    mBinding.tvNoDishesAddedYet.visibility = View.VISIBLE
                }
                // END
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_all_dishes, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_add_dish -> {
                startActivity(Intent(requireActivity(), AddUpdateDishActivity::class.java))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
