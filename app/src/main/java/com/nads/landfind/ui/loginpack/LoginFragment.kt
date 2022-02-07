package com.nads.landfind.ui.loginpack

import android.content.Context
import android.os.Bundle
import android.util.Log
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
import com.google.protobuf.EmptyProto
import com.nads.landfind.EventObserver
import com.nads.landfind.R
import com.nads.landfind.UserPreferences
import com.nads.landfind.data.UserPreferencesSerializer
import com.nads.landfind.databinding.FragmentLoginBinding
import com.nads.landfind.ui.activities.MainActivity
import com.nads.landfind.util.setupSnackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class LoginFragment : Fragment() {




    private val Context.userDataStore: DataStore<UserPreferences> by dataStore(
        fileName = DATA_STORE_FILE_NAME,
        serializer = UserPreferencesSerializer
    )


    companion object {
        private const val USER_PREFERENCES_NAME = "user_preferences"
        private const val DATA_STORE_FILE_NAME = "user_prefs.pb"
        private const val SORT_ORDER_KEY = "sort_order"


    }
  


    private val loginViewModel: LoginViewModel by navGraphViewModels(R.id.login_nav) {defaultViewModelProviderFactory}
    private lateinit var fragmentLoginBinding: FragmentLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentLoginBinding= FragmentLoginBinding.inflate(inflater, container, false).apply {
            loginviewmodel = loginViewModel
            lifecycleOwner = activity

        }
        setupToolbar()
       fragmentLoginBinding.signUp.setOnClickListener {
           findNavController().navigate(R.id.signUp)
       }

        isLoggedIn()
        login()


        return fragmentLoginBinding.root

    }




    private fun login(){
        loginViewModel.navigated.observe(
            viewLifecycleOwner, EventObserver{
                lifecycleScope.launch {
                    updateUsername(loginViewModel.username.value.toString())

                }
                findNavController().navigate(R.id.mainFragment)

            }
        )
    }



    private fun isLoggedIn() {
        lifecycleScope.launch {
            val datas = requireActivity().userDataStore.data.collectLatest {
                if (!it.username.isNullOrBlank()){
                    findNavController().navigate(R.id.mainFragment)
                }

            }

        }
    }

    private fun setupSnackbar() {
        view?.setupSnackbar(this, loginViewModel.snackbarText, Snackbar.LENGTH_SHORT)
    }
    suspend fun updateUsername(username:String) {
        requireActivity().userDataStore.updateData { preferences ->
            preferences.toBuilder().setUsername(username).build()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupSnackbar()
    }

    fun setupToolbar(){
        requireActivity().appBarLayout.visibility = View.GONE
    }



}