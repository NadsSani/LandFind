package com.nads.landfind.ui.loginpack

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navGraphViewModels
import com.google.android.material.snackbar.Snackbar
import com.nads.landfind.EventObserver
import com.nads.landfind.R
import com.nads.landfind.UserPreferences
import com.nads.landfind.data.UserPreferencesSerializer
import com.nads.landfind.databinding.OtpFragBinding
import com.nads.landfind.util.setupSnackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch
@AndroidEntryPoint
class Otp :Fragment(){


    val args: OtpArgs by navArgs()

    private val Context.userDataStore: DataStore<UserPreferences> by dataStore(
        fileName = DATA_STORE_FILE_NAME,
        serializer = UserPreferencesSerializer
    )


    companion object {
        private const val USER_PREFERENCES_NAME = "user_preferences"
        private const val DATA_STORE_FILE_NAME = "user_prefs.pb"
        private const val SORT_ORDER_KEY = "sort_order"


    }
    private val otpViewModel: OtpViewModel by navGraphViewModels(R.id.login_nav) {defaultViewModelProviderFactory}
    private lateinit var otpFragBinding: OtpFragBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        otpFragBinding = OtpFragBinding.inflate(inflater, container, false).apply {
            signupviewmodel = otpViewModel
            lifecycleOwner = viewLifecycleOwner

        }

        setUpOtp(args.username.toString())
        setupSnackbar()
        setupToolbar()
        return otpFragBinding.root
    }
    fun setupToolbar(){
        requireActivity().appBarLayout.visibility = View.GONE
    }
    private fun setupSnackbar() {
        view?.setupSnackbar(this, otpViewModel.snackbarText, Snackbar.LENGTH_SHORT)
    }
    private fun setUpOtp(arg:String) {
        otpViewModel.navigated.observe(
            viewLifecycleOwner, EventObserver{
                updateUsername(arg)
                findNavController().navigate(R.id.mainFragment)



            })
    }

    fun updateUsername(username:String) {
        lifecycleScope.launch {
            requireActivity().userDataStore.updateData {
                    preferences ->
                preferences.toBuilder().setUsername(username).build()
            }

        }


    }
}