package com.udacity.shoestore.destination

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoeViewModel: ViewModel() {


    //Internal
    private var _shoeList = MutableLiveData<ArrayList<Shoe>>()

    //External
    val shoeList: LiveData<ArrayList<Shoe>>
    get() = _shoeList

    init {
        _shoeList.value = ArrayList<Shoe>()
    }
    fun addShoe(newShoe: Shoe){
        if(newShoe != null){
            Timber.i("Added New Shoe..${newShoe}")
        _shoeList.value?.add(newShoe)
        }

    }

}