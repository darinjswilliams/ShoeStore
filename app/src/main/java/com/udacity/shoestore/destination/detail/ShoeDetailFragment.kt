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
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)

        binding.shoeAddButton.setOnClickListener {
            binding.apply {
                shoe.company = shoeCompanyText.text.toString()
                shoe.name = shoeNameText.text.toString()
                shoe.size = shoeSizeText.text.toString().toDouble()
                shoe.description = shoeInventoryDescription.text.toString()
                shoeViewModel.addShoe(shoe)
                Timber.i("Added shoe $shoe")
            }
            findNavController().navigate(R.id.action_shoeDetailFragment_to_shoeDestinationFragment)
        }


        binding.shoeCancelButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_shoeDetailFragment_to_shoeDestinationFragment)
        )

        return binding.root
    }


}