package com.nads.landfind.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.navGraphViewModels
import com.google.android.material.snackbar.Snackbar
import com.nads.landfind.R
import com.nads.landfind.databinding.FragmentMainBinding
import com.nads.landfind.databinding.ProfileFragmentBinding
import com.nads.landfind.ui.mainviewmodel.MainViewModel
import com.nads.landfind.util.setupSnackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Profile: Fragment() {

    private lateinit var profileFragmentBinding: ProfileFragmentBinding
    private val profileViewModel: ProfileViewModel by
    navGraphViewModels<ProfileViewModel>(R.id.login_nav){
        defaultViewModelProviderFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        profileFragmentBinding = ProfileFragmentBinding.inflate(inflater, container, false).apply {
            profileviewmodel = profileViewModel
        }
        return profileFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupSnackbar() {
        view?.setupSnackbar(this, profileViewModel.snackbarText, Snackbar.LENGTH_SHORT)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupSnackbar()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
    }
}