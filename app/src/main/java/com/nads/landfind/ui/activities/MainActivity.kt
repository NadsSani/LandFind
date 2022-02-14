package com.nads.landfind.ui.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.navigation.NavigationView
import com.nads.landfind.R
import com.nads.landfind.UserPreferences
import com.nads.landfind.data.UserPreferencesSerializer
import com.nads.landfind.ui.mainviewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {


    private val Context.userDataStore: DataStore<UserPreferences> by dataStore(
        fileName = DATA_STORE_FILE_NAME,
        serializer = UserPreferencesSerializer
    )


    companion object {
        private const val USER_PREFERENCES_NAME = "user_preferences"
        private const val DATA_STORE_FILE_NAME = "user_prefs.pb"
        private const val SORT_ORDER_KEY = "sort_order"


    }

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var toolbars: Toolbar
    private lateinit var navController:NavController
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition() {
                viewModel.isLoading.value
            }
        }



        setContentView(R.layout.activity_main)
        setupNavigationDrawer()
        toolbars = findViewById(R.id.toolbar)

        actionBar?.setHomeButtonEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)

       setSupportActionBar(toolbars)


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.mainnavigation) as NavHostFragment


         navController = navHostFragment.navController


        //Note here this may be the cause for navigation drawer not working
        appBarConfiguration =
            AppBarConfiguration(setOf(R.id.buyer, R.id.mainFragment,R.id.seller),drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        findViewById<NavigationView>(R.id.navView)
            .setupWithNavController(navController)
    }


  suspend fun updateUsername2(username:String) {
        userDataStore.updateData { preferences ->
            preferences.toBuilder().setUsername(null).build()
        }
    }


    private fun setupNavigationDrawer() {
        drawerLayout = (findViewById<DrawerLayout>(R.id.drawerLayoutmain))
            .apply {
                setStatusBarBackground(R.color.landfindyellow)
            }

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        TODO("Not yet implemented")
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.title ==  "Logout" ){


        }
        return NavigationUI.onNavDestinationSelected(item, navController) || super.onOptionsItemSelected(item)
    }
}