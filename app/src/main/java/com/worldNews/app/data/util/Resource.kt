package com.worldNews.app.data.util

sealed class Resource<out R> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val error: Throwable) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}


