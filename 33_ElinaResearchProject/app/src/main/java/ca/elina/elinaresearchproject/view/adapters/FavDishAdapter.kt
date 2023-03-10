package ca.elina.elinaresearchproject.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import ca.elina.elinaresearchproject.databinding.ItemDishLayoutBinding
import ca.elina.elinaresearchproject.model.entities.FavDish
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

        //  Assign the click event to the itemview and perform the required action.
        holder.itemView.setOnClickListener {
            // TODO Step 3: Add the else condition for Favorite Dishes Fragment as below and call the function to navigate the item to Details Fragment.
            // START
            if (fragment is AllDishesFragment) {
                fragment.dishDetails(dish) // Pass the required param value.
            } else if (fragment is FavoriteDishesFragment) {
                fragment.dishDetails(dish)
            }
            // END
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
    }
}
