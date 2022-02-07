package com.nads.landfind.data.source

interface LandFindDataSource {

    suspend fun getLandList(){}
    suspend fun login(){}
    suspend fun signup(){}
    suspend fun submitLand(){}



}