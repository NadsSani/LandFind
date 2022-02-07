package com.nads.landfind.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.nads.landfind.MainCoroutineRule
import com.nads.landfind.data.Land
import com.nads.landfind.data.source.local.LandFindDao
import com.nads.landfind.data.source.local.LandFindDataBase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import com.nads.landfind.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.junit.runner.RunWith
import kotlin.coroutines.ContinuationInterceptor

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class LandFindDaoTest {
    private lateinit var database: LandFindDataBase

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()
    private lateinit var dao: LandFindDao

    @After
    fun closeDb() = database.close()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    @Before
    fun initDb() {
        // using an in-memory database because the information stored here disappears when the
        // process is killed
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            LandFindDataBase::class.java
        ).allowMainThreadQueries().build()
        dao = database.landfinddao()

    }
    @Test
    fun insertItem()= runBlockingTest {
        val landitem = Land(1,"chavara","https://quran.com",
            "https://quran.com","https://quran.com","sidewall","king",
            "https://quran.com","nothing","goodspec",
            "no","","",
            "","")
        dao.insert(landitem)
        val landitems = dao.getLandList()

    }

}


