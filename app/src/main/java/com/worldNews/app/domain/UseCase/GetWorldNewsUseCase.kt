package com.worldNews.app.domain.UseCase

import com.worldNews.app.data.model.Article
import com.worldNews.app.data.util.Resource
import com.worldNews.app.domain.repository.WorldNewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetWorldNewsUseCase @Inject constructor(private val worldNewsRepository: WorldNewsRepository) {

    suspend fun fetchWorldNewsFromApi(country: String): Flow<Resource<List<Article>>> =
        flow {
            emit(Resource.Loading)
            val responses = worldNewsRepository.getWorldNews(country)
            responses?.let { emit(it) }
        }.catch {
            emit(Resource.Error(it))
        }

    suspend fun saveFavouriteWorldNews(data: Article) =
        worldNewsRepository.saveWorldNewsToCache(data)


    fun getLiveFavouriteWorldNnewss(): Flow<List<Article>> =
        worldNewsRepository.getWorldNewsFromCache()

    suspend fun deleteFavouriteWorldNewsByTitleAndDate(title:String,publishedAt: String) =
        worldNewsRepository.deleteFavouriteWorldNewsFromCache(title=title,publishedAt=publishedAt)

}