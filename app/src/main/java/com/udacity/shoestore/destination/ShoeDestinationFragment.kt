package com.udacity.shoestore.destination

import android.os.Bundle
import android.view.*
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.snackbar.Snackbar
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDestinationBinding
import com.udacity.shoestore.databinding.ShoetextviewBinding
import com.udacity.shoestore.models.Shoe
import timber.log.Timber


class ShoeDestinationFragment : Fragment() {

    private lateinit var binding: FragmentShoeDestinationBinding
    private val shoeViewModel: ShoeViewModel by activityViewModels()
    private lateinit var vBinding: ShoetextviewBinding
    private lateinit var mLinearLayout: LinearLayout
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_destination, container, false)

        //Get reference to viewModel
        Timber.i("Called ViewModelProvider")

        //this line of code allows you to use livedata to automatically update your livedata layouts
//        binding.setLifecycleOwner(this)


        mLinearLayout = binding.shoeItems

        binding.fab.setOnClickListener { view: View ->
            findNavController().navigate(ShoeDestinationFragmentDirections.actionShoeDestinationFragmentToShoeDetailFragment())
        }

        shoeViewModel.shoe.observe(viewLifecycleOwner, Observer { shoe ->
            if (shoe == null) {
                Snackbar.make(binding.root, "No Shoe Added", Snackbar.LENGTH_SHORT)
            } else {
                //iterate the shoe
                shoe.forEach {
                    //Add child view programmatically
                    vBinding = ShoetextviewBinding.inflate(inflater, container, false)

                    vBinding.apply {
                        shoeNamesText.text = it.name
                        shoeCompaniesText.text = it.company
                        shoeDescritionText.text = it.description
                        shoeSizesText.text = it.size.toString()
                        mLinearLayout.addView(shoeInventoryView)
                        Timber.i("Shoes added to inventory")
                    }

                }
            }

        })

//        binding.shoeViewModel = shoeViewModel

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        Timber.i("Create Menu")
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

}