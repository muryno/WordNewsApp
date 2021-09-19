package com.worldNews.app.presenter.di

import android.content.Context
import androidx.room.Room
import com.worldNews.app.data.db.WNDBDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseAppModule {

    @Singleton
    @Provides
    fun provideShoppingItemDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, WNDBDatabase::class.java, "WeightWatcher").build()


    @Singleton
    @Provides
    fun provideWeightWatcher(
        database: WNDBDatabase
    ) = database.worldNewsDao()



}

















