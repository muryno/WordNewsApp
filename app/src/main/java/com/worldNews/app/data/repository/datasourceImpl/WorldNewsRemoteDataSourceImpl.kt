package com.worldNews.app.data.repository.datasourceImpl

import com.worldNews.app.data.api.WorldNewsService
import com.worldNews.app.data.model.WorldNews
import com.worldNews.app.data.repository.datasource.WorldNewsRemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class WorldNewsRemoteDataSourceImpl @Inject constructor(
    private val worldNewsService: WorldNewsService,
) : WorldNewsRemoteDataSource {
    override suspend fun getWorldNewsFromApi(country: String): Response<WorldNews> =
        worldNewsService.getWorldNewsApi(country = country )
}

