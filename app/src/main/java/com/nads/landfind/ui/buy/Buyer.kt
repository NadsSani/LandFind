package com.nads.landfind.ui.buy

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.nads.landfind.EventObserver
import com.nads.landfind.adapters.LandFinderBaseAdapter
import com.nads.landfind.databinding.FragBuyerBinding
import com.nads.landfind.ui.base.BaseFrag
import com.nads.landfind.util.setupSnackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.frag_buyer.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import android.widget.AdapterView
import android.widget.Button
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.nads.landfind.R


@AndroidEntryPoint
class Buyer: BaseFrag() {
    private val taluks = arrayOf("Thiruvananthapuram",
        "Nedumangadu",
        "Chirayinkeezhu",
        "Kattakada",
        "Neyyattinkara",
        "Varkala")
    private lateinit var fragBuyerBinding: FragBuyerBinding
    private val buyerViewModel: BuyerViewModel by navGraphViewModels(R.id.login_nav)
    { defaultViewModelProviderFactory }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragBuyerBinding = FragBuyerBinding.inflate(inflater, container, false).apply{
        buyerviewmodel = buyerViewModel
       lifecycleOwner = viewLifecycleOwner

        }


        hideKeyboard()


        setHasOptionsMenu(true)

        buyerViewModel.navigated.observe(
         viewLifecycleOwner, EventObserver {
              val action = BuyerDirections.actionBuyerToLandDetails(id = it.toString())
                findNavController().navigate(action)
            }
        )
        buyerViewModel.dialogue.observe(viewLifecycleOwner,EventObserver{
            setUpFilter()
        })
        buyerViewModel.isLoading.observe(viewLifecycleOwner){
            if (it) fragBuyerBinding?.progressloader?.llLoading?.visibility = View.VISIBLE
            else fragBuyerBinding?.progressloader?.llLoading?.visibility = View.GONE
        }
        setUpAppBar()
        fragBuyerBinding.appBarLayout.visibility = View.VISIBLE
        setUpRecyclerView()

        return fragBuyerBinding.root
    }

    private fun setUpRecyclerView(hbtaluk: String? =null, village: String?=null, price:String?=null) {
        val viewAdapter = LandFinderBaseAdapter(LandFinderBaseAdapter.UserComparator,buyerViewModel)
        fragBuyerBinding.listLands.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = viewAdapter
        }
        lifecycleScope.launch {

            buyerViewModel.loadData(null,
                null,
                hbtaluk,
                village,
                null,
                null,
                null,
                price, this)?.collectLatest { pagingData ->
                viewAdapter.submitData(pagingData)
            }

        }
    }
    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }



    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }





    private fun setUpFilter() {
        val builder: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(context)
        val mView = View.inflate(context, R.layout.filter_page, null)
        val taluk: Spinner = mView.findViewById(R.id.taluks_spinner)
        val village: Spinner = mView.findViewById(R.id.village_spinner)
        val price: Spinner = mView.findViewById(R.id.price_spinner)
        val applyFilter: Button = mView.findViewById<Button>(R.id.applyfilterbtn)

        ArrayAdapter<CharSequence>(requireActivity(), android.R.layout.simple_spinner_item, buyerViewModel.taluks)
                .also { adapter ->
                    // Specify the layout to use when the list of choices appears
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    // Apply the adapter to the spinner
                    taluk.adapter = adapter
                }
        setUpTaluk(taluk,village)

        ArrayAdapter<CharSequence>(requireActivity(), android.R.layout.simple_spinner_item, buyerViewModel.price)
            .also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                price.adapter = adapter
            }

        builder.setView(mView)
        val alertDialog: android.app.AlertDialog? = builder.create()
        setUpApplyFilter(alertDialog,applyFilter,taluk,village,price)
        alertDialog?.show()

    }

    private fun setUpApplyFilter(
        alertDialog: AlertDialog?,
        applyFilter: Button,
        taluk: Spinner,
        village: Spinner,
        price: Spinner
    ) {
             applyFilter.setOnClickListener {
                 val talukes = taluk.selectedItem.toString()
                 val villages = village.selectedItem.toString()
                 val prices = price.selectedItem.toString()
                 setUpRecyclerView(talukes,villages,prices)
                 alertDialog?.cancel()
             }
    }


    private fun setUpTaluk(taluk:Spinner,village:Spinner){
        taluk.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (buyerViewModel.taluks[position] ){
                    "Thiruvananthapuram"-> setUpVillageSp(buyerViewModel.tvmtaluk,village)
                    "Nedumangadu" ->setUpVillageSp(buyerViewModel.ndmTaluk,village)
                    "Chirayinkeezhu" ->setUpVillageSp(buyerViewModel.chuTaluk,village)
                    "Kattakada" ->setUpVillageSp(buyerViewModel.kattakTaluk,village)
                    "Neyyattinkara"->setUpVillageSp(buyerViewModel.neyyattinkaraTaluk,village)
                    "Varkala" ->setUpVillageSp(buyerViewModel.varkalaTaluk,village)
                    else -> setUpVillageSp(buyerViewModel.tvmtaluk,village)
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }



     private fun setUpVillageSp(arr: kotlin.Array<String>, village:Spinner){

         ArrayAdapter<CharSequence>(requireActivity(), android.R.layout.simple_spinner_item, arr)
             .also { adapter ->
                 // Specify the layout to use when the list of choices appears
                 adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                 // Apply the adapter to the spinner
                 village.adapter = adapter
             }
     }
    private fun setUpAppBar() {
        requireActivity().appBarLayout.visibility = View.GONE

    }



    private fun setupSnackbar() {
        view?.setupSnackbar(this, buyerViewModel.snackbarText, Snackbar.LENGTH_SHORT)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupSnackbar()
    }

    override fun onResume() {
        super.onResume()
        setUpRecyclerView()
    }

    override fun onDestroy() {
        super.onDestroy()
       // fragBuyerBinding.appBarLayout.visibility = View.GONE
       // requireActivity().appBarLayout.visibility = View.VISIBLE
    }
}