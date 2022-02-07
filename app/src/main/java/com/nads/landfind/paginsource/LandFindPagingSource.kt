package com.nads.landfind.paginsource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.work.ListenableWorker
import com.nads.landfind.data.Land
import com.nads.landfind.data.Result
import com.nads.landfind.data.source.LandFindRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LandFindPagingSource @Inject constructor(
    private val  landFindDefaultRepository: LandFindRepository,
    private val Id:String?,
    private val  place_name:String?,
    private val   village:String?,
    private val   hbtaluk:String?,
    private val  propertiesland:String?,
    private val   otherspec:String?,
    private val  landtype:String?,
    private val   price:String?)
    : PagingSource<Int, Land>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Land> {
        try {
            // Start refresh at page 1 if undefined.
            val nextPageNumber = params.key ?: 1

            val respons = landFindDefaultRepository.getLandList(Id,
                place_name,
                village,
                hbtaluk,
                propertiesland,
                otherspec,
                landtype,
                price,
                nextPageNumber)
            val responseData = mutableListOf<Land>()
           if (respons is Result.Success)
           {
               val data = respons.data.datavalues

            responseData.addAll(data)
           }
            return LoadResult.Page(
                data = responseData,
                prevKey = null, // Only paging forward.
                nextKey = nextPageNumber.plus(1)
            )
        } catch (e: Exception) {
            return LoadResult.Page(
                data = emptyList(),
                prevKey = null, // Only paging forward.
                nextKey = null
            )
            // Handle errors in this block and return LoadResult.Error if it is an
            // expected error (such as a network failure).
        }

    }

    override fun getRefreshKey(state: PagingState<Int, Land>): Int? {
        TODO("Not yet implemented")
    }


}