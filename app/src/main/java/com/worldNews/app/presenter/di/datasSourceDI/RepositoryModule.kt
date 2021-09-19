package com.worldNews.app.presenter.di.datasSourceDI

import com.worldNews.app.data.repository.WorldNewsWatcherRepositoryImpl
import com.worldNews.app.data.repository.datasource.WorldNewsCacheDataSource
import com.worldNews.app.data.repository.datasource.WorldNewsRemoteDataSource
import com.worldNews.app.domain.repository.WorldNewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun providerRepository(
        worldNewsRemoteDataSource: WorldNewsRemoteDataSource,

        WorldNewsCacheDataSource: WorldNewsCacheDataSource


    ): WorldNewsRepository {
        return WorldNewsWatcherRepositoryImpl(
            worldNewsRemoteDataSource = worldNewsRemoteDataSource,
            WorldNewsCacheDataSource = WorldNewsCacheDataSource
        )
    }
}