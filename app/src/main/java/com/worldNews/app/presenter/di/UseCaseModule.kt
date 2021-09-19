package com.worldNews.app.presenter.di

import com.worldNews.app.domain.UseCase.GetWorldNewsUseCase
import com.worldNews.app.domain.repository.WorldNewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Singleton
    @Provides
    fun provideGetUsedCase(weightWaterRepository: WorldNewsRepository) =
        GetWorldNewsUseCase(weightWaterRepository)
}