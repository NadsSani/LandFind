package com.nads.landfind.data.source.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.nads.landfind.data.Land
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class LandFindDaoTest {

    private lateinit var database: LandFindDataBase
    private lateinit var dao: LandFindDao

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
    @After
    fun teardown(){
        database.close()
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