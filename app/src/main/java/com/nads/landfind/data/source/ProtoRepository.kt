package com.nads.landfind.data.source

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.nads.landfind.UserPreferences
import com.nads.landfind.data.UserPreferencesSerializer
import com.nads.landfind.ui.loginpack.LoginFragment
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton
@Singleton
class ProtoRepository @Inject constructor(
    @ApplicationContext private val context: Context){

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