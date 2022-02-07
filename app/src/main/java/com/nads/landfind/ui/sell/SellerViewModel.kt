package com.nads.landfind.ui.sell

import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ListenableWorker
import com.nads.landfind.Event
import com.nads.landfind.R
import com.nads.landfind.data.Result
import com.nads.landfind.data.source.ProtoRepository
import com.nads.landfind.domain.GetLoginUseCases
import com.nads.landfind.domain.GetSubmitUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import javax.inject.Inject
@HiltViewModel
class SellerViewModel @Inject constructor (private val getSubmitUseCases: GetSubmitUseCases
):ViewModel() {
    val username = MutableLiveData<String>()
    val placename = MutableLiveData<String>()
    val village = MutableLiveData<String>()
    val taluk = MutableLiveData<String>()
    val propertiesonland = MutableLiveData<String>()
    val googlemaplocation = MutableLiveData<String>()
    val otherspecification = MutableLiveData<String>()
    val leagalissues = MutableLiveData<String>()
    val contactnumber = MutableLiveData<String>()
    val estimatedprice = MutableLiveData<String>()
    val landtype = MutableLiveData<String>()
    val address = MutableLiveData<String>()
    val submit = MutableLiveData<String>()
    val image= MutableLiveData<String>()
    val image2=MutableLiveData<String>()
    val image3=MutableLiveData<String>()
    private val _snackbarText = MutableLiveData<Event<Int>>()
    val snackbarText: LiveData<Event<Int>> = _snackbarText
     var _id = MutableLiveData<Event<Int>>()
    val id: LiveData<Event<Int>> = _id
    var _navigated = MutableLiveData<Event<Unit>>()
    val navigated: LiveData<Event<Unit>> = _navigated



    fun submit(){
            viewModelScope.launch {

               val data = getSubmitUseCases(username.value,image.value,image2.value
                   ,image3.value,googlemaplocation.value,placename.value,village.value,taluk.value
                   ,propertiesonland.value,otherspecification.value,landtype.value,
                   leagalissues.value,contactnumber.value,address.value,estimatedprice.value)
                if (data is Result.Success){
                  _navigated.value = Event(Unit)
                }

            }
    }







    fun chooseImage(view: View){
        val getdi =  view.id
      _id.value = Event(getdi)

    }

}