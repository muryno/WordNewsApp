package com.worldNews.app.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.worldNews.app.data.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface WorldNewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllWorldNews(wnList: List<Article>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFavouriteWorldNews(favourite: Article)

    @Query("DELETE FROM article ")
    suspend fun deleteAllFavouriteWorldNews()

    @Query("DELETE FROM article WHERE title=:title AND publishedAt=:publishedAt")
    suspend fun deleteFavouriteWorldNews(title:String,publishedAt: String)

    @Query("SELECT * FROM article")
    fun getFavouriteWorldNews(): Flow<List<Article>>
}