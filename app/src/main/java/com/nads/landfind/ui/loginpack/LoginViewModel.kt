package com.nads.landfind.ui.loginpack

import android.util.Log
import androidx.lifecycle.*
import androidx.work.ListenableWorker
import com.nads.landfind.Event
import com.nads.landfind.R
import com.nads.landfind.data.Result
import com.nads.landfind.data.source.ProtoRepository
import com.nads.landfind.domain.GetLoginUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor (private val getLoginUseCases: GetLoginUseCases):ViewModel() {

   init {
       Log.i("SplashViewModel", "SplashViewModel created!")

   }
    private val _snackbarText = MutableLiveData<Event<Int>>()
    val navigated: LiveData<Event<Unit>>
        get() = _navigated
    private val _navigated = MutableLiveData<Event<Unit>>()
    val snackbarText: LiveData<Event<Int>> = _snackbarText

    val username = MutableLiveData<String>()
    val _username:LiveData<String> = username
    val password = MutableLiveData<String>()
    val _password : LiveData<String> = password

    fun login(){
        if (username.value == null  || password.value == null){
            _snackbarText.value = Event(R.string.empty_username_password)
              return
        }
        else{
               viewModelScope.launch {
                   val data = getLoginUseCases(username = username.value!!, password = password.value!!)
                   if (data is Result.Success) {
                       _navigated.value = Event(Unit)
                   }
                   else{
                       _snackbarText.value = Event(R.string.error_username_password)
                   }


               }
        }

    }
}