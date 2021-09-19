package com.worldNews.app.presenterTest

import com.worldNews.app.FakeCarbonWeightWatcherData
import com.worldNews.app.data.model.Article
import com.worldNews.app.data.util.Resource
import com.worldNews.app.domain.repository.WorldNewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UseCaseRepositoryMock : WorldNewsRepository {



    override suspend fun getWorldNews(country: String): Resource<List<Article>>? {
        val dummyData: List<Article> =
            FakeCarbonWeightWatcherData.carbonWeightWatcher

        return Resource.Success(dummyData)

    }

    override fun getWorldNewsFromCache(): Flow<List<Article>> {

        val dummyData: List<Article> =
            FakeCarbonWeightWatcherData.carbonWeightWatcher

        return flow {
            emit(dummyData)
        }
    }

    override suspend fun saveWorldNewsToCache(wnLists: Article) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFavouriteWorldNewsFromCache(title: String, publishedAt: String) {
        TODO("Not yet implemented")
    }
}