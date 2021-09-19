package com.worldNews.app.data.api


import com.worldNews.app.BuildConfig
import com.worldNews.app.data.model.WorldNews
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WorldNewsService {

    @GET("top-headlines")
    suspend fun getWorldNewsApi(
        @Query("country")
        country:String,
        @Query("apiKey")
        apiKey:String = BuildConfig.API_KEY
    ): Response<WorldNews>

}