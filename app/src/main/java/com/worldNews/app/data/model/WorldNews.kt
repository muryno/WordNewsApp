package com.worldNews.app.data.model

data class WorldNews(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)