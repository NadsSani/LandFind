package com.nads.landfind.ui.loginpack

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nads.landfind.Event
import com.nads.landfind.R
import com.nads.landfind.data.Result
import com.nads.landfind.domain.GetSignUpUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val getSignUpUseCases: GetSignUpUseCases):ViewModel() {
    private val _snackbarText = MutableLiveData<Event<Int>>()
    val snackbarText: LiveData<Event<Int>> = _snackbarText
    val username = MutableLiveData<String>()

    private val _navigated = MutableLiveData<Event<Unit>>()
    val navigated: LiveData<Event<Unit>>
        get() = _navigated
    val password = MutableLiveData<String>()

    fun login(){
        if (username.value == null  || password.value == null){
            _snackbarText.value = Event(R.string.empty_username_password)
            return
        }
        else{
            viewModelScope.launch {
               val data = getSignUpUseCases(username = username.value!!, password = password.value!!)
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