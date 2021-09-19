package com.worldNews.app.domainModule


import com.google.common.truth.Truth.assertThat
import com.worldNews.app.FakeCarbonWeightWatcherData
import com.worldNews.app.data.model.Article
import com.worldNews.app.data.util.Resource
import com.worldNews.app.domain.UseCase.GetWorldNewsUseCase
import com.worldNews.app.domain.repository.WorldNewsRepository
import com.worldNews.app.utils.BaseUnitTest
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

@HiltAndroidTest
@ExperimentalCoroutinesApi
class UseCaseTest : BaseUnitTest() {

    private lateinit var weightWaterRepository: WorldNewsRepository
    private lateinit var getWorldNewsUseCase: GetWorldNewsUseCase

    private var weightWatcherModelItem = FakeCarbonWeightWatcherData.fakeArticleResponse

    @Before
    fun setUp() {
        weightWaterRepository = Mockito.mock(WorldNewsRepository::class.java)
        getWorldNewsUseCase = GetWorldNewsUseCase(weightWaterRepository)
    }

    @Test
    fun executeGetWorldNewsNotEqualNullTest() = runBlocking {
        Mockito.`when`(weightWaterRepository.getWorldNews("us")).thenReturn(
            Resource.Success(
                weightWatcherModelItem
            )
        )
        val reponse: Resource<List<Article>>? =
            weightWaterRepository.getWorldNews("us")

        assertThat(reponse).isNotNull()
    }


    @Test
    fun executeGetWorldNewsFromCacheCaseNotEqualNullTest() = runBlocking {
        Mockito.`when`(weightWaterRepository.getWorldNewsFromCache()).thenReturn(
            flow {
                emit(weightWatcherModelItem)
            }
        )
        assertThat(
            weightWaterRepository.getWorldNewsFromCache().count()
        ).isGreaterThan(
            0
        )
    }

    @Test
    fun executeSaveWorldNewsToCacheTest() = runBlocking {
        Mockito.`when`(weightWaterRepository.saveWorldNewsToCache(weightWatcherModelItem[0])).thenReturn(Unit)
        val response: Unit = weightWaterRepository.saveWorldNewsToCache(weightWatcherModelItem[0])
        assertThat(response).isNotNull()
    }

    @Test
    fun executeDeleteFavouriteWorldNewsFromCacheTest() = runBlocking {

        val article : Article = weightWatcherModelItem[0]
        Mockito.`when`(weightWaterRepository.deleteFavouriteWorldNewsFromCache(title = article.title.toString(),publishedAt =
        article.publishedAt.toString()
        )).thenReturn(Unit)
        val response: Unit = weightWaterRepository.deleteFavouriteWorldNewsFromCache(title = article.title.toString(),publishedAt =
        article.publishedAt.toString()
        )
        assertThat(response).isNotNull()
    }
}