package com.nads.landfind.ui.buy

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.*
import androidx.work.ListenableWorker
import com.hadilq.liveevent.LiveEvent
import com.nads.landfind.Event
import com.nads.landfind.data.Result.Success
import com.nads.landfind.R
import com.nads.landfind.adapters.LandFinderAdapter
import com.nads.landfind.data.DataValues
import com.nads.landfind.data.Land
import com.nads.landfind.data.Result
import com.nads.landfind.data.source.LandFindDefaultRepository
import com.nads.landfind.domain.GetLandUseCases
import com.nads.landfind.domain.GetLandsUseCase
import com.nads.landfind.model.BannerModel
import com.nads.landfind.model.BuyerListModel
import com.nads.landfind.model.ItemViewModel
import com.nads.landfind.paginsource.LandFindPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BuyerViewModel @Inject constructor (private val getLandsUseCases: GetLandsUseCase,private val repository: LandFindDefaultRepository): ViewModel() {
    val data: LiveData<List<ItemViewModel>>
        get() = _data
    private val _data = MutableLiveData<List<ItemViewModel>>(emptyList())
    var isLoading = LiveEvent<Boolean>()
   val tvmtaluk = arrayOf("Andoorkonam","Attipra","Ayiroopara","Cheruvakkal","Kadakampally"
        ,"Kadinamkulam","Kalliyoor","Kazhakoottam"
        ,"Keezhthonakkal","Kowdiar","Kudappanakunnu","Manacaud","Melthonnakkal"
        ,"Menamkulam","Muttathara","Nemom","Pallipuram","Pangappara","Pattom","Peroorkada"
        ,"Pettah","Sasthamangalam","Thirumala","Thiruvallam","Thycaud","Uliyazhthura","Ulloor",
        "Vanchiyoor","Vattiyoorkavu"
        ,"Veiloor","Venganoor")
   val ndmTaluk = arrayOf("Anad", "Aruvikkara", "Aryanad", "Kallara"," Karakulam"," Karippooru",
        "Koliyakode", "Kurupuzha", "Manikkal", "Nedumangad", "Nellanad"," Palode",
        "Panavoor", "Pangode", "Peringamala", "Pullampara", "Theakada", "Thennoor", "Tholicode",
        "Uzhamalackal", "Vamanapuram", "Vattappara", "Vellanad", "Vembayam", "Vithura")
     val chuTaluk = arrayOf("Alamkodu",
        "Anjuthengu",
        "Attingal – Avanavancheri",
        "Azhoor	",
        "Edakodu",
        "Elamba – Mudakkal",
        "Kadakkavoor",
        "Karavaram",
        "Keezhattingal",
        "Kilimanoor",
        "Kizhuvilam – Koonthalloor",
        "Koduvazanoor",
        "Nagaroor",
        "Pazhayakunnummel",
        "Pulimathu",
        "Sarkara – Chirayinkeezhu",
        "Vakkom",
        "Vellalloor")
     val kattakTaluk = arrayOf("Mannoorkara",
        "Perumkulam	","Veeranakavu","Amboori","Kallikad"
        ,"Keezharoor","Kulathummal","Malayinkeezhu"
        ,"Maranalloor"
        ,"Ottasekharamangalam","Vazhichal","Vilappil"
        ,"Vilavoorkal")
    val neyyattinkaraTaluk = arrayOf("Anavoor	","Athiyannoor	","Balaramapuram","Chenkal"
        ,"Kanjiramkulam","Karode"
        ,"Karumkulam","Kollayil	","Kottukal","Kulathoor","Kunnathukal","Neyyattinkara",
        "Pallichal","Parasala","Parasuvakkal","Perumkadavila","Perumpazhuthoor","Poovar",
        "Thirupuram","Vellarada","Vizhinjam")
    val varkalaTaluk = arrayOf("Ayiroor","Chemmaruthi","Cherunniyoor","Edava",
        "Kudavoor","Madavoor","Manamboor","Navaikulam","Ottoor","Pallikkal","Varkala","Vettoor")
   val taluks = arrayOf("Thiruvananthapuram",
            "Nedumangadu",
            "Chirayinkeezhu",
            "Kattakada",
            "Neyyattinkara",
            "Varkala")
    val price = arrayOf("0 - 1000000",
        "1000000 - 5000000 ",
        "5000000 - 10000000",
        "10000000 - 10000000",
        "10000000 - 500000000",
        "5000000000 and Above")

    private val _snackbarText = MutableLiveData<Event<Int>>()
    val snackbarText: LiveData<Event<Int>> = _snackbarText

    val navigated: LiveData<Event<String>>
        get() = _navigated
    private val _navigated = MutableLiveData<Event<String>>()
    val dialogue: LiveData<Event<Unit>>
        get() = _dialogue
    private val _dialogue = MutableLiveData<Event<Unit>>()
    init {
//        loadData(null,
//            null,
//            null,
//            null,
//            null,
//        null,
//        null,
//        null,)

    }



      fun loadData(Id:String?,
                         placename:String?,
                         village:String?,
                         hbtaluk:String?,
                         propertiesland:String?,
                         otherspec:String?,
                         landtype:String?,
                         price:String?,viewModelScope: CoroutineScope): Flow<PagingData<Land>> ?{
          isLoading.postValue( true)
              val landlists = getLandsUseCases(
                  Id,
                  placename,
                  village,
                  hbtaluk,
                  propertiesland,
                  otherspec,
                  landtype,
                  price,viewModelScope
              )

              if (landlists is Success) {
                  var data = landlists.data
                  isLoading.postValue( false)
                   return data
              }
          isLoading.postValue( false)
          return null
      }

    fun navigateToScreen(id:String){

        _navigated.value = Event(id)
    }

    fun openFilterDialogue(){

    _dialogue.value = Event(Unit)

    }


}