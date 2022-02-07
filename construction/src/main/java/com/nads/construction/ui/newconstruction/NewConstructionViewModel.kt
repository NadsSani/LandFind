package com.nads.construction.ui.newconstruction

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nads.landfind.Event

class NewConstructionViewModel:ViewModel() {
    var _navigatehouse= MutableLiveData<Event<Unit>>()
    val navigatehouse: LiveData<Event<Unit>> = _navigatehouse
    var _navigatecommercial= MutableLiveData<Event<Unit>>()
    val navigatecommercial: LiveData<Event<Unit>> = _navigatecommercial

    fun navigateHouse(){
        _navigatehouse.value = Event(Unit)
    }
    fun navigateCommercial(){
        _navigatecommercial.value = Event(Unit)
    }



}