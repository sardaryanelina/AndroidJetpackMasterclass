package ca.elina.elinaresearchproject.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ca.elina.elinaresearchproject.R
import androidx.lifecycle.Observer
import ca.elina.elinaresearchproject.view.activities.AddUpdateDishActivity
import ca.elina.elinaresearchproject.viewmodel.HomeViewModel

class AllDishesFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    // Override the onCreate function and enable setHasOptionMenu to add the action menu to Fragment.
    // START
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    // END

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_all_dishes, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    // Override the onCreateOptionMenu and onOptionsItemSelected methods and launch the AddUpdateDishActivity on selection.
    // START
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_all_dishes, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    // happens when one of the objects is clicked (find it by itemId)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_add_dish -> {
                // requireActivity() will give us the fragment's activity, similar to "this"
                startActivity(Intent(requireActivity(), AddUpdateDishActivity::class.java))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    // END
}