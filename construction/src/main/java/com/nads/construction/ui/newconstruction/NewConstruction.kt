package com.nads.construction.ui.newconstruction

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.navGraphViewModels
import com.nads.construction.BR.newconstructionviewmodel
import com.nads.construction.R
import com.nads.construction.databinding.FragmentNewConstructionBinding
import com.nads.landfind.EventObserver
import com.nads.landfind.databinding.FragLanddetailsBinding
import com.nads.landfind.ui.landdetails.LandDetailsViewModel

class NewConstruction : Fragment() {
    private val newConstructionViewModel: NewConstructionViewModel by navGraphViewModels(com.nads.landfind.R.id.login_nav)
    { defaultViewModelProviderFactory }
    private lateinit var fragmentNewConstructionBinding: FragmentNewConstructionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentNewConstructionBinding= FragmentNewConstructionBinding.inflate(inflater, container, false).apply {
            newconstructionviewmodel = newConstructionViewModel
            lifecycleOwner = activity

        }
        setUpNav(fragmentNewConstructionBinding)
        setUpNavigation()

        return fragmentNewConstructionBinding.root
    }

    private fun setUpNavigation() {
        newConstructionViewModel.navigatehouse.observe(viewLifecycleOwner,EventObserver{setUpNewConstruction()})

        newConstructionViewModel.navigatecommercial.observe(viewLifecycleOwner,EventObserver{setUpNewConstruction()})
    }

    private fun setUpNewConstruction(){
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$8075467438")
        }
        startActivity(intent)
    }


    private fun setUpNav(fragmentNewConstructionBinding: FragmentNewConstructionBinding){

    }


}