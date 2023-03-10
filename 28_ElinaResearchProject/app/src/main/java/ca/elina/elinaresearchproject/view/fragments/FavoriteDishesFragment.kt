package ca.elina.elinaresearchproject.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import ca.elina.elinaresearchproject.R

// DashboardFragment refactored to FavoriteFragment.
class FavoriteDishesFragment : Fragment() {

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
}