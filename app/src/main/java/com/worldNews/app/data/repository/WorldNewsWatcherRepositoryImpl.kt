package com.worldNews.app.data.repository

import android.util.Log
import com.worldNews.app.data.model.Article
import com.worldNews.app.data.repository.datasource.WorldNewsCacheDataSource
import com.worldNews.app.data.repository.datasource.WorldNewsRemoteDataSource
import com.worldNews.app.data.util.Resource
import com.worldNews.app.domain.repository.WorldNewsRepository
import com.worldNews.app.utils.ResponseToResourceUtils
import kotlinx.coroutines.flow.Flow

class WorldNewsWatcherRepositoryImpl(
    private val worldNewsRemoteDataSource: WorldNewsRemoteDataSource,
    private val WorldNewsCacheDataSource: WorldNewsCacheDataSource
) : WorldNewsRepository {

    override suspend fun getWorldNews(country: String): Resource<List<Article>> = getWorldNewsFromAPI(country)

    override fun getWorldNewsFromCache(): Flow<List<Article>> =
        WorldNewsCacheDataSource.getFavouriteWorldNewsFromCache()

    override suspend fun saveWorldNewsToCache(wnLists: Article) =
        WorldNewsCacheDataSource.saveFavouriteWorldNews(wnLists)

    override suspend fun deleteFavouriteWorldNewsFromCache(title: String, publishedAt: String) =
        WorldNewsCacheDataSource.deleteFavouriteWorldNews(title = title, publishedAt = publishedAt)


    private suspend fun getWorldNewsFromAPI(country: String): Resource<List<Article>> {
        try {
            val response = worldNewsRemoteDataSource.getWorldNewsFromApi(country)
            return ResponseToResourceUtils.responseToResource(response, null)
        } catch (exception: Throwable) {
            Log.i("MyTag", exception.message.toString())
            ResponseToResourceUtils.responseToResource(null, exception)
        }
        return ResponseToResourceUtils.responseToResource(null, Throwable())
    }
}