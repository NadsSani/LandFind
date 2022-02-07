package com.nads.landfind.ui.landdetails

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.helper.widget.Carousel
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.PagerSnapHelper
import com.google.android.material.card.MaterialCardView
import com.google.android.material.snackbar.Snackbar
import com.nads.landfind.R
import com.nads.landfind.databinding.FragLanddetailsBinding
import com.nads.landfind.databinding.FragSellerBinding
import com.nads.landfind.ui.sell.SellerViewModel
import com.nads.landfind.util.setupSnackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.frag_seller.*
@AndroidEntryPoint
class LandDetails: Fragment() {
    private val landDetailsViewModel: LandDetailsViewModel by navGraphViewModels(R.id.login_nav)
    { defaultViewModelProviderFactory }
    val args: LandDetailsArgs by navArgs()
    private lateinit var fragLanddetailsBinding: FragLanddetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragLanddetailsBinding= FragLanddetailsBinding.inflate(inflater, container, false).apply {
            landdetailsviewmodel = landDetailsViewModel
            lifecycleOwner = activity

        }
        val id  = args.id
        id?.let { landDetailsViewModel.getId(it) }
        landDetailsViewModel.isLoading.observe(viewLifecycleOwner){
            if (it) fragLanddetailsBinding?.progressloader?.llLoading?.visibility = View.VISIBLE
            else fragLanddetailsBinding?.progressloader?.llLoading?.visibility = View.GONE
        }
        setUpScroll()
        return fragLanddetailsBinding.root
    }

    private fun setUpScroll() {
        PagerSnapHelper().attachToRecyclerView(fragLanddetailsBinding.landdetailsbanner)

        Handler(Looper.getMainLooper()).postDelayed({
            fragLanddetailsBinding.indicator.attachToRecyclerView(fragLanddetailsBinding.landdetailsbanner)
        }, 2000)
    }


    private fun setupSnackbar() {
        view?.setupSnackbar(this, landDetailsViewModel.snackbarText, Snackbar.LENGTH_SHORT)
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

}