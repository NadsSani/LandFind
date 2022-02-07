package com.nads.construction.ui.maintenancework

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.navigation.navGraphViewModels
import com.bumptech.glide.Glide
import com.nads.construction.R
import com.nads.construction.databinding.FragmentMaintenanceWorkBinding
import com.nads.landfind.EventObserver
import com.nads.landfind.databinding.FragLanddetailsBinding
import com.nads.landfind.ui.landdetails.LandDetailsViewModel


class MaintenanceWork : Fragment() {
    private val maintanenceViewModel: MaintanenceViewModel by navGraphViewModels(com.nads.landfind.R.id.login_nav)
    { defaultViewModelProviderFactory }
    private lateinit var fragmentMaintenanceWorkBinding: FragmentMaintenanceWorkBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentMaintenanceWorkBinding = FragmentMaintenanceWorkBinding.inflate(inflater, container, false).apply {
            maintananceviewmodel = maintanenceViewModel
            lifecycleOwner = activity

        }
        navigatetophonecall()

        return fragmentMaintenanceWorkBinding.root
    }

    private fun navigatetophonecall() {
        maintanenceViewModel.navigateac.observe(viewLifecycleOwner,EventObserver{clickphone()})
        maintanenceViewModel.navigatecorecuttin.observe(viewLifecycleOwner,EventObserver{clickphone()})
        maintanenceViewModel.navigatedrainage.observe(viewLifecycleOwner,EventObserver{clickphone()})
        maintanenceViewModel.navigatelectrical.observe(viewLifecycleOwner,EventObserver{clickphone()})
        maintanenceViewModel.navigateplumbing.observe(viewLifecycleOwner,EventObserver{clickphone()})
    }

    fun clickphone(
    )
    {
           val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:$8075467438")
            }
            startActivity(intent)
    }

}