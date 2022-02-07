package com.nads.construction.ui.maintenancework

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nads.landfind.Event

class MaintanenceViewModel :ViewModel() {

    var _navigatedrainage = MutableLiveData<Event<Unit>>()
    val navigatedrainage : LiveData<Event<Unit>> = _navigatedrainage
    var _navigatelectrical= MutableLiveData<Event<Unit>>()
    val navigatelectrical: LiveData<Event<Unit>> = _navigatelectrical
    var _navigateplumbing= MutableLiveData<Event<Unit>>()
    val navigateplumbing: LiveData<Event<Unit>> = _navigateplumbing
    var _navigateac= MutableLiveData<Event<Unit>>()
    val navigateac: LiveData<Event<Unit>> = _navigateac
    var _navigatecorecutting= MutableLiveData<Event<Unit>>()
    val navigatecorecuttin: LiveData<Event<Unit>> = _navigatecorecutting




    fun navigateDrainage(){
        _navigatedrainage.value = Event(Unit)
    }
    fun navigateAc(){
        _navigateac.value = Event(Unit)
    }
    fun navigateElectrical(){
        _navigatelectrical.value = Event(Unit)
    }
    fun navigatePlumbing(){
        _navigateplumbing.value = Event(Unit)
    }
    fun navigateCoreCutting(){
        _navigatecorecutting.value = Event(Unit)
    }















}