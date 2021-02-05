package com.udacity.shoestore.destination

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoeViewModel : ViewModel() {


    //Internal
    private var _shoeList = MutableLiveData<MutableList<Shoe>>()
    private var shoeInventoryList: MutableList<Shoe> = mutableListOf(
        Shoe(
            "Skeaters", 10.5,
            "Skeaters", "Causal Shoes"
        ),
        Shoe(
            "Walkers", 11.5,
            "Boots", "Boots"
        )
    )
    private var _isValidShoe = MutableLiveData<Boolean>()
    private var _cancelShoe = MutableLiveData<Boolean>()

    //External
    val shoe: LiveData<MutableList<Shoe>>
        get() = _shoeList

    val cancelShoe: LiveData<Boolean>
    get() = _cancelShoe

    val isValidShoe : LiveData<Boolean>
    get() = _isValidShoe

    init {
        _shoeList.value = shoeInventoryList
    }

    fun addShoe(shoe: Shoe?) {
        if (shoe != null) {
            //Turn switch to true
            _isValidShoe.value = true

            Timber.i("Added New Shoe..${shoe}")
            shoeInventoryList.add(shoe)

            _shoeList.value = shoeInventoryList
        }
        Timber.i("Shoe is empty...${shoe}")
    }

    fun cancelShoe(){
        _isValidShoe.value = true
    }

    fun validateInventory(){
        _isValidShoe.value = false
        _cancelShoe.value = false
    }


}