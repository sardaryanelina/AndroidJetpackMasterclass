package ca.elina.elinaresearchproject.view.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import ca.elina.elinaresearchproject.R
import ca.elina.elinaresearchproject.databinding.ItemDishLayoutBinding
import ca.elina.elinaresearchproject.model.entities.FavDish
import ca.elina.elinaresearchproject.utils.Constants
import ca.elina.elinaresearchproject.view.activities.AddUpdateDishActivity
import ca.elina.elinaresearchproject.view.fragments.AllDishesFragment
import ca.elina.elinaresearchproject.view.fragments.FavoriteDishesFragment
import com.bumptech.glide.Glide

class FavDishAdapter(private val fragment: Fragment) :
    RecyclerView.Adapter<FavDishAdapter.ViewHolder>() {

    private var dishes: List<FavDish> = listOf()

    /**
     * Inflates the item views which is designed in xml layout file
     *
     * create a new
     * {@link ViewHolder} and initializes some private fields to be used by RecyclerView.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemDishLayoutBinding =
            ItemDishLayoutBinding.inflate(LayoutInflater.from(fragment.context), parent, false)
        return ViewHolder(binding)
    }

    /**
     * Binds each item in the ArrayList to a view
     *
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     *
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val dish = dishes[position]

        // Load the dish image in the ImageView.
        Glide.with(fragment)
            .load(dish.image)
            .into(holder.ivDishImage)

        holder.tvTitle.text = dish.title

        holder.itemView.setOnClickListener {
            if (fragment is AllDishesFragment) {
                fragment.dishDetails(dish) // Pass the required param value.
                // Call the function to navigate the item to Details Fragment.
            } else if (fragment is FavoriteDishesFragment) {
                fragment.dishDetails(dish)
            }
        }

        if (fragment is AllDishesFragment) {
            holder.ibMore.visibility = View.VISIBLE
        } else if (fragment is FavoriteDishesFragment) {
            holder.ibMore.visibility = View.GONE
        }

        holder.ibMore.setOnClickListener {
            val popup = PopupMenu(fragment.context, holder.ibMore)
            //Inflating the Popup using xml file
            popup.menuInflater.inflate(R.menu.menu_adapter, popup.menu)

            popup.setOnMenuItemClickListener {
                if (it.itemId == R.id.action_edit_dish) {
                    // TODO Step 2: Replace the Log with below code to pass the dish details to AddUpdateDishActivity.
                    // START
                    val intent =
                        Intent(fragment.requireActivity(), AddUpdateDishActivity::class.java)
                    intent.putExtra(Constants.EXTRA_DISH_DETAILS, dish)
                    fragment.requireActivity().startActivity(intent)
                    // END
                } else if (it.itemId == R.id.action_delete_dish) {
                    Log.i("You have clicked on", "Delete Option of ${dish.title}")
                }
                true
            }

            popup.show() //showing popup menu
        }
    }

    /**
     * Gets the number of items in the list
     */
    override fun getItemCount(): Int {
        return dishes.size
    }

    fun dishesList(list: List<FavDish>) {
        dishes = list
        notifyDataSetChanged()
    }

    /**
     * A ViewHolder describes an item view and metadata about its place within the RecyclerView.
     */
    class ViewHolder(view: ItemDishLayoutBinding) : RecyclerView.ViewHolder(view.root) {
        // Holds the TextView that will add each item to
        val ivDishImage = view.ivDishImage
        val tvTitle = view.tvDishTitle
        val ibMore = view.ibMore
    }
}
