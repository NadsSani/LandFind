package com.nads.landfind.paginsource

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.nads.landfind.data.Land
import com.nads.landfind.data.source.LandFindServices
import com.nads.landfind.data.source.local.LandFindDataBase

@OptIn(ExperimentalPagingApi::class)
class LandRemoteMediator(
    private val query: String,
    private val database: LandFindDataBase,
    private val networkService: LandFindServices
) : RemoteMediator<Int, Land>() {
    val landFindDao = database.landfinddao()
    override suspend fun load(loadType: LoadType, state: PagingState<Int, Land>): MediatorResult {
        TODO("Not yet implemented")
    }


}










