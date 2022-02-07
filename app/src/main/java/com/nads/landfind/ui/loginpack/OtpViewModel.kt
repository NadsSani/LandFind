package com.nads.landfind.ui.loginpack

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nads.landfind.Event
import com.nads.landfind.R
import com.nads.landfind.data.Result
import com.nads.landfind.domain.GetOtpUseCases
import com.nads.landfind.domain.GetSignUpUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class OtpViewModel @Inject constructor(private val getOtpUseCases: GetOtpUseCases):ViewModel() {

    private val _snackbarText = MutableLiveData<Event<Int>>()
    val snackbarText: LiveData<Event<Int>> = _snackbarText
    var otp = MutableLiveData<StringBuilder>()
    var otp1 = MutableLiveData<String>()
    var otp2 = MutableLiveData<String>()
    var otp3 = MutableLiveData<String>()
    var otp4 = MutableLiveData<String>()

    val navigated: LiveData<Event<Unit>>
        get() = _navigated
    private val _navigated = MutableLiveData<Event<Unit>>()

    fun login(){
        if (otp1.value!!.isEmpty() && otp2.value!!.isEmpty() && otp3.value!!.isEmpty() && otp4.value!!.isEmpty()){
            _snackbarText.value = Event(R.string.empty_username_password)
            return
        }
        else{
            viewModelScope.launch {
                stringBuilder()
                val data = getOtpUseCases(otp.value.toString())
                if (data is Result.Success) {
                    _navigated.value = Event(Unit)
                }
                else{
                    _snackbarText.value = Event(R.string.error_username_password)
                }

            }
        }
    }
    fun stringBuilder(){

     otp.value =   otp.value?.append(otp1)
           ?.append(otp2)
           ?.append(otp3)
           ?.append(otp4)
   }

}