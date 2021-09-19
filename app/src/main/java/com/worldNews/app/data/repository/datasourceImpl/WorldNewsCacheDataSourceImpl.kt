package com.worldNews.app.data.repository.datasourceImpl


import com.worldNews.app.data.db.WorldNewsDao
import com.worldNews.app.data.model.Article
import com.worldNews.app.data.repository.datasource.WorldNewsCacheDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WorldNewsCacheDataSourceImpl @Inject constructor(val worldNewsDao: WorldNewsDao) :
    WorldNewsCacheDataSource {

    override fun getFavouriteWorldNewsFromCache(): Flow<List<Article>> = worldNewsDao.getFavouriteWorldNews()

    override suspend fun saveFavouriteWorldNews(wnLists: Article) =  worldNewsDao.saveFavouriteWorldNews(wnLists)

    override suspend fun deleteAllFromWorldNews() =  worldNewsDao.deleteAllFavouriteWorldNews()

    override suspend fun deleteFavouriteWorldNews(title: String, publishedAt: String) =  worldNewsDao.deleteFavouriteWorldNews(title = title, publishedAt = publishedAt)
}