package com.worldNews.app.presenter.di

import com.worldNews.app.BuildConfig
import com.worldNews.app.data.api.WorldNewsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetWorkModuleDI() {

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideWWService(retrofit: Retrofit): WorldNewsService {
        return retrofit.create(WorldNewsService::class.java)
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
       return OkHttpClient.Builder()
            .connectTimeout(62, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .build()
                chain.proceed(newRequest)

            }.addInterceptor(interceptor).build()
    }

}