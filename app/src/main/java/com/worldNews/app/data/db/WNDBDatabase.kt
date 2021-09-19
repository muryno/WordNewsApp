package com.worldNews.app.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.worldNews.app.data.model.Article


@Database(
    entities = [Article::class],
    version = 1,
    exportSchema = false
)
abstract class WNDBDatabase : RoomDatabase() {
    abstract fun worldNewsDao(): WorldNewsDao
}