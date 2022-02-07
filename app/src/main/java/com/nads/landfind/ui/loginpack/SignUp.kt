package com.nads.landfind.ui.loginpack

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.google.android.material.snackbar.Snackbar
import com.nads.landfind.EventObserver
import com.nads.landfind.R
import com.nads.landfind.UserPreferences
import com.nads.landfind.data.UserPreferencesSerializer
import com.nads.landfind.databinding.FragmentLoginBinding
import com.nads.landfind.databinding.FragmentSignUpBinding
import com.nads.landfind.ui.buy.BuyerDirections
import com.nads.landfind.util.setupSnackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignUp : Fragment() {
    private val Context.userDataStore: DataStore<UserPreferences> by dataStore(
        fileName = DATA_STORE_FILE_NAME,
        serializer = UserPreferencesSerializer
    )

    companion object {
        private const val USER_PREFERENCES_NAME = "user_preferences"
        private const val DATA_STORE_FILE_NAME = "user_prefs.pb"
        private const val SORT_ORDER_KEY = "sort_order"


    }

    private val signUpViewModel: SignUpViewModel by navGraphViewModels(R.id.login_nav) {defaultViewModelProviderFactory}
    private lateinit var fragmentSignUpBinding: FragmentSignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentSignUpBinding= FragmentSignUpBinding.inflate(inflater, container, false).apply {
            loginviewmodel = signUpViewModel
            lifecycleOwner = viewLifecycleOwner

        }
        signup()
        setupToolbar()
        fragmentSignUpBinding.buttonsignup.setOnClickListener {
            findNavController().navigate(R.id.mainFragment)
        }
       return fragmentSignUpBinding.root
    }
    fun setupToolbar(){
        requireActivity().appBarLayout.visibility = View.GONE
    }
    private fun setupSnackbar() {
        view?.setupSnackbar(this, signUpViewModel.snackbarText, Snackbar.LENGTH_SHORT)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupSnackbar()
    }
    private fun signup(){
        signUpViewModel.navigated.observe(
            viewLifecycleOwner, EventObserver{
                val action = SignUpDirections.actionSignUpToOtp(signUpViewModel.username.value.toString())
                findNavController().navigate(action)
            }
        )
    }

}