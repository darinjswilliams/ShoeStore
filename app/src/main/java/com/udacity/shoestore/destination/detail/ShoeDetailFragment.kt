package com.udacity.shoestore.destination.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.destination.ShoeDestinationFragmentDirections
import com.udacity.shoestore.destination.ShoeViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber


class ShoeDetailFragment : Fragment() {

    private val shoeViewModel: ShoeViewModel by activityViewModels()
    private lateinit var binding: FragmentShoeDetailBinding
    private var shoe = Shoe("", 0.0, "", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)

        //Pass the ShoeViewModel into the binding.shoemodel so provide communication with xml
        binding.shoeModel = shoeViewModel

        //Make LifeCycleAware
        binding.setLifecycleOwner(this)

        //two way databinding
        binding.shoe = shoe

        shoeViewModel.isValidShoe.observe(viewLifecycleOwner, { checkForValidShoe ->
            if (checkForValidShoe) {
                navigationToRoute()
                shoeViewModel.validateInventory()
            }
        })

        shoeViewModel.cancelShoe.observe(viewLifecycleOwner, { userCancelShoeRequest ->
            if (userCancelShoeRequest) {
                navigationToRoute()
                shoeViewModel.validateInventory()
            }

        })


        return binding.root
    }


    private fun navigationToRoute() {
        //Since the navigation is simple, just go back to previous screen by use navigateUp
        findNavController().navigateUp()
    }
}