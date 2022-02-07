package com.nads.landfind.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.nads.landfind.R
import com.nads.landfind.databinding.FragmentMainBinding
import com.nads.landfind.ui.mainviewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainFragment : Fragment() {
    private lateinit var mainFragmentMainBinding: FragmentMainBinding
    private val mainViewModel:MainViewModel by navGraphViewModels<MainViewModel>(R.id.login_nav){
        defaultViewModelProviderFactory
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mainFragmentMainBinding = FragmentMainBinding.inflate(inflater, container, false).apply {
            mainviewmodel = mainViewModel
        }
        setHasOptionsMenu(true)
        setupToolbar()
        setUpOptions(mainFragmentMainBinding)

        return mainFragmentMainBinding.root
    }

    private fun setUpOptions(mainFragmentMainBinding: FragmentMainBinding) {
           mainFragmentMainBinding.buyerlink.setOnClickListener {
               findNavController().navigate(R.id.buyer)
           }
           mainFragmentMainBinding.sellerlink.setOnClickListener{
               findNavController().navigate(R.id.seller)
           }
           mainFragmentMainBinding.maintenencelink.setOnClickListener {
               findNavController().navigate(R.id.construction_graph)
           }
    }

    fun setupToolbar(){
        requireActivity().appBarLayout.visibility = View.VISIBLE
    }
}