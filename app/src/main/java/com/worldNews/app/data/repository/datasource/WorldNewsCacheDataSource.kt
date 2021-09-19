package com.worldNews.app.data.repository.datasource

import com.worldNews.app.data.model.Article
import kotlinx.coroutines.flow.Flow


interface WorldNewsCacheDataSource {

     fun getFavouriteWorldNewsFromCache(): Flow<List<Article>>
    suspend fun saveFavouriteWorldNews(wnLists: Article)
    suspend fun deleteAllFromWorldNews()
    suspend fun deleteFavouriteWorldNews(title:String,publishedAt: String)
}