package com.nads.landfind.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nads.landfind.data.Land

@Database(entities = [Land::class], version = 1, exportSchema = false)
abstract class LandFindDataBase : RoomDatabase() {

    abstract fun landfinddao(): LandFindDao
}