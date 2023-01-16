package ca.elina.elinaresearchproject.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import ca.elina.elinaresearchproject.R
import ca.elina.elinaresearchproject.databinding.FragmentRandomDishBinding

// NotificationsFragment refactored to RandomDishFragment.
class RandomDishFragment : Fragment() {

    private var mBinding: FragmentRandomDishBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentRandomDishBinding.inflate(inflater, container, false)
        return mBinding!!.root
    }

    // Override the onDestroy method and make the ViewBinding null when it is called.
    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }
}