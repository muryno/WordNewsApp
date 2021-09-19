package com.worldNews.app.di

import android.content.Context
import androidx.room.Room
import com.worldNews.app.data.db.WNDBDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseAppModule  {

    @Provides
    @Singleton
    @Named("test_db")
    fun provideInMemoryDb(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(context, WNDBDatabase::class.java)
            .allowMainThreadQueries()
            .build()


}