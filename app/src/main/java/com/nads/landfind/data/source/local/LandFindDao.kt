package com.nads.landfind.data.source.local

import androidx.paging.PagingSource
import androidx.room.*
import com.nads.landfind.data.Land


@Dao
interface LandFindDao {

    @Query("SELECT * FROM landtable")
    fun getLandList(): List<Land>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(land: Land)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(lands: List<Land>)

    @Delete
    suspend fun deleteAll(lands: Land)

    @Query("SELECT * FROM landtable")
    fun pagingSource(): PagingSource<Int, Land>

}