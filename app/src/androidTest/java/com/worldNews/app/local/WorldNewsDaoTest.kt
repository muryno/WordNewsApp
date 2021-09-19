package com.worldNews.app.local

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.google.common.truth.Truth.assertThat
import com.worldNews.app.FakeCarbonWeightWatcherData
import com.worldNews.app.data.db.WNDBDatabase
import com.worldNews.app.data.db.WorldNewsDao
import com.worldNews.app.data.model.Article
import com.worldNews.app.utils.BaseUnitAndroidTest
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch
import javax.inject.Inject
import javax.inject.Named

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
@MediumTest
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class WorldNewsDaoTest : BaseUnitAndroidTest() {


    private lateinit var articleDao: WorldNewsDao

    private var fakeArticle: List<Article> = FakeCarbonWeightWatcherData.fakeArticleResponse

    @Inject
    @Named("test_db")
    lateinit var database: WNDBDatabase

    @Before
    fun setup() {
        articleDao = database.worldNewsDao()
    }


    @Test
    fun testFakeArticleIssNotEmpty() = runBlockingTest {
        assertThat(fakeArticle.size).isGreaterThan(1)
    }


    @Test
    fun testSavedArticle() = runBlocking {
        articleDao.saveAllWorldNews(fakeArticle)

        val latch = CountDownLatch(1)
        val job = async(Dispatchers.IO) {
            articleDao.getFavouriteWorldNews().collect {
                assertThat(it.size).isGreaterThan(1)
                latch.countDown()
            }
        }
        latch.await()
        job.cancelAndJoin()


    }

    @Test
    fun deleteAllSavedArticle() = runBlocking {
        //fetch saved article and test if data exist
        val latch = CountDownLatch(1)

        //save first
        articleDao.saveAllWorldNews(fakeArticle)
        articleDao.deleteAllFavouriteWorldNews()

        val job1 = async(Dispatchers.IO) {
            articleDao.getFavouriteWorldNews().collect {
                assertThat(it.size).isLessThan(1)
                latch.countDown()
            }
        }

        latch.await()
        job1.cancelAndJoin()
    }


    @After
    fun teardown() {
        database.close()
    }

}













