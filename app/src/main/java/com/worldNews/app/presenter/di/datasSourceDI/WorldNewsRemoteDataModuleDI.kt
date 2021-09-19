package com.worldNews.app.presenter.di.datasSourceDI

import com.worldNews.app.data.api.WorldNewsService
import com.worldNews.app.data.db.WorldNewsDao
import com.worldNews.app.data.repository.datasource.WorldNewsCacheDataSource
import com.worldNews.app.data.repository.datasource.WorldNewsRemoteDataSource
import com.worldNews.app.data.repository.datasourceImpl.WorldNewsCacheDataSourceImpl
import com.worldNews.app.data.repository.datasourceImpl.WorldNewsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class WorldNewsRemoteDataModuleDI {

    @Singleton
    @Provides
    fun providersRemoteDataSource(wwDbServiceCarbon: WorldNewsService): WorldNewsRemoteDataSource {
        return WorldNewsRemoteDataSourceImpl(
            wwDbServiceCarbon
        )
    }


    @Singleton
    @Provides
    fun  provideLocalDataSource(worldNewsDao : WorldNewsDao): WorldNewsCacheDataSource{
        return WorldNewsCacheDataSourceImpl(worldNewsDao)
    }
}