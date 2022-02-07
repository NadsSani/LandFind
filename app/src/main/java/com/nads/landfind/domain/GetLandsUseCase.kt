package com.nads.landfind.domain


import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.nads.landfind.data.Land
import com.nads.landfind.data.Result
import com.nads.landfind.data.Result.Success
import com.nads.landfind.data.source.LandFindRepository
import com.nads.landfind.paginsource.LandFindPagingSource
import com.nads.landfind.util.wrapEspressoIdlingResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class GetLandsUseCase @Inject constructor(private val  landFindDefaultRepository: LandFindRepository) {


    operator fun invoke(Id:String?,
                                  place_name:String?,
                                  village:String?,
                                  hb_taluk:String?,
                                  properties_land:String?,
                                  other_spec:String?,
                                  land_type:String?,
                                  price:String?,scope: CoroutineScope): Result<Flow<PagingData<Land>>> {

          wrapEspressoIdlingResource {

              val landslist =    Pager(
                  config = PagingConfig(
                      pageSize = 5,
                      enablePlaceholders = false,
                      prefetchDistance = 20
                  ),
                  pagingSourceFactory = {  LandFindPagingSource(landFindDefaultRepository,Id,
                      place_name,
                      village,
                      hb_taluk,
                      properties_land,
                      other_spec,
                      land_type,
                      price ) }
              ).flow.cachedIn(scope)



             return Success(landslist)

          }

      }

}