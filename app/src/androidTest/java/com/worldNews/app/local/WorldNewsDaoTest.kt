package com.worldNews.app.local

import androidx.test.filters.SmallTest
import com.worldNews.app.data.db.WNDBDatabase
import com.worldNews.app.data.db.WorldNewsDao
import com.worldNews.app.utils.BaseUnitAndroidTest
import com.google.common.truth.Truth.assertThat
import com.worldNews.app.data.model.Article
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class WorldNewsDaoTest : BaseUnitAndroidTest() {


    private lateinit var daoCarbon: WorldNewsDao


    @Inject
    @Named("test_db")
    lateinit var database: WNDBDatabase

    @Before
    fun setup() {
        daoCarbon = database.worldNewsDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertWeightWatcherItem() = runBlockingTest {
        val weightWatcherModelItem = listOf<Article>(
//            WorldNewsItem(0, "filter", "imga", "testing"),
//            WorldNewsItem(1, "filterTwo", "imga", "testing")
        )

        daoCarbon.saveAllWorldNews(weightWatcherModelItem)


        val allWeightWatcher = daoCarbon.getFavouriteWorldNews().count()

        assertThat(allWeightWatcher).isGreaterThan(0)
    }

    @Test
    fun deleteWeightWatcherItem() = runBlockingTest {
        val weightWatcherModelItem = arrayListOf<Article>(
//            WorldNewsItem(0, "filter", "imga", "testing"),
//            WorldNewsItem(1, "filterTwo", "imga", "testing")
        )
        daoCarbon.saveAllWorldNews(weightWatcherModelItem)
        daoCarbon.deleteAllFavouriteWorldNews()

//        val allWeightWatcher = dao.getWeightWatcher().getOrAwaitValue()
//
//        assertThat(allWeightWatcher).isEmpty()
    }


}













