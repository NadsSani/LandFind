package com.nads.landfind.ui.profile

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.snackbar.Snackbar
import com.nads.landfind.Event
import com.nads.landfind.util.setupSnackbar
import dagger.hilt.android.lifecycle.HiltViewModel


class ProfileViewModel:ViewModel() {

    private val _snackbarText = MutableLiveData<Event<Int>>()
    val snackbarText: LiveData<Event<Int>> = _snackbarText




}