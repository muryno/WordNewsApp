package com.worldNews.app.utils

import com.worldNews.app.data.model.Article
import com.worldNews.app.data.model.WorldNews
import com.worldNews.app.data.util.Resource

import retrofit2.Response

class ResponseToResourceUtils {
    companion object {
        internal fun responseToResource(
            response: Response<WorldNews>?,
            exception: Throwable?
        ): Resource<List<Article>> {
            if (response != null && response.isSuccessful) {

                response.body()?.articles?.let { result ->
                    return Resource.Success(result)
                }
            }
                return Resource.Error((exception ?:"") as Throwable)

        }
    }
}