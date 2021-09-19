package com.worldNews.app.data.repository.datasource


import com.worldNews.app.data.model.WorldNews
import retrofit2.Response

interface WorldNewsRemoteDataSource {
    suspend fun getWorldNewsFromApi(country: String): Response<WorldNews>

}