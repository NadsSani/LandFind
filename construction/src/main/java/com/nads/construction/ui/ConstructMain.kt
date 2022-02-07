package com.nads.construction.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.nads.construction.R
import com.nads.construction.databinding.FragmentConstructMainBinding
import com.nads.construction.databinding.FragmentNewConstructionBinding
import com.nads.construction.ui.constructviewmodel.ConstructViewModel
import com.nads.construction.ui.newconstruction.NewConstructionViewModel


class ConstructMain : Fragment() {
    private val constructViewModel: ConstructViewModel by navGraphViewModels(com.nads.landfind.R.id.login_nav)
    { defaultViewModelProviderFactory }
    private lateinit var fragmentConstructMainBinding: FragmentConstructMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentConstructMainBinding= FragmentConstructMainBinding.inflate(inflater, container, false).apply {
            constructviewmodel = constructViewModel
            lifecycleOwner = activity

        }
        setUpNav(fragmentConstructMainBinding)

        return fragmentConstructMainBinding.root    }

    private fun setUpNav(fragmentConstructMainBinding: FragmentConstructMainBinding) {
       fragmentConstructMainBinding.newconstructionbtn.setOnClickListener {
           findNavController().navigate(R.id.newConstruction2)
        }
        fragmentConstructMainBinding.maintenencebtn.setOnClickListener {
          findNavController().navigate(R.id.maintenanceWork2)
        }

    }



}