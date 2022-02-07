package com.nads.landfind

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.nads.landfind.data.UserPreferencesSerializer
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class LandFinderApplication: Application() {
    private val Context.userDataStore: DataStore<UserPreferences> by dataStore(
        fileName = DATA_STORE_FILE_NAME,
        serializer = UserPreferencesSerializer
    )


    companion object {
        private const val USER_PREFERENCES_NAME = "user_preferences"
        private const val DATA_STORE_FILE_NAME = "user_prefs.pb"
        private const val SORT_ORDER_KEY = "sort_order"


    }

}