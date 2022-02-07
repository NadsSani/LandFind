package com.nads.landfind.ui.landdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hadilq.liveevent.LiveEvent
import com.nads.landfind.Event
import com.nads.landfind.R
import com.nads.landfind.data.DataValues
import com.nads.landfind.data.Land
import com.nads.landfind.data.Result
import com.nads.landfind.domain.GetLandUseCases
import com.nads.landfind.model.BannerModel
import com.nads.landfind.model.ItemViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LandDetailsViewModel @Inject constructor(private val getLandUseCases: GetLandUseCases): ViewModel() {
    var isLoading = LiveEvent<Boolean>()
    private val _snackbarText = MutableLiveData<Event<Int>>()
    val snackbarText: LiveData<Event<Int>> = _snackbarText

    val data: LiveData<List<ItemViewModel>>
        get() = _data
    private val _data = MutableLiveData<List<ItemViewModel>>(emptyList())
    val id: LiveData<String>
        get() = _id
    private val _id = MutableLiveData<String>()
    val land: LiveData<Land>
        get() = _land
    private val _land = MutableLiveData<Land>()

    val placename = MutableLiveData<String>()
    val village = MutableLiveData<String>()
    val taluks = MutableLiveData<String>()
    val landtype = MutableLiveData<String>()
    val propertiesonland = MutableLiveData<String>()
    val maplocation = MutableLiveData<String>()
    val price = MutableLiveData<String>()

    private fun onDataLoaded(land:Land){
        loadData(land)
          placename.value = "Place Name: "+land.placename
        village.value = "Village: "+land.village
        taluks.value = "Taluks: "+land.hbtaluk
        landtype.value = "Land Type: "+land.landtype
        propertiesonland.value = "Properties On Land: "+land.propertiesland
        maplocation.value = "Google Map: "+land.googlemap
        price.value = "Price: "+land.price
    }


    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading
    init {
        //loadData()
    }

    private fun loadData(land: Land) {
        // This is a coroutine scope with the lifecycle of the ViewModel
        isLoading.postValue( true)
        viewModelScope.launch {
            _data.postValue(listOf(BannerModel(land.img1)
                , BannerModel(land.img2),BannerModel
                    (land.img3)))
        }
    }
  fun getId(id:String){
      
       viewModelScope.launch {
           getLandUseCases(id).let { result->
               if (result is Result.Success){
                  onDataLoaded(result.data.datavalues[0])
               }else{
                  onDataNotAvailable()
               }

           }
           isLoading.postValue( false)
       }
    }

    private fun onDataNotAvailable() {
        _dataLoading.value = false
    }


}