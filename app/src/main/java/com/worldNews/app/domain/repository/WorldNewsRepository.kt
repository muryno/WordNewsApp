package com.worldNews.app.domain.repository

import com.worldNews.app.data.model.Article
import com.worldNews.app.data.util.Resource
import kotlinx.coroutines.flow.Flow


interface WorldNewsRepository {
    suspend fun getWorldNews(country: String): Resource<List<Article>>?
    fun getWorldNewsFromCache(): Flow<List<Article>>
    suspend fun saveWorldNewsToCache(wnLists: Article)
    suspend fun deleteFavouriteWorldNewsFromCache(title:String,publishedAt: String)
}