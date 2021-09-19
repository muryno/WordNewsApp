package com.worldNews.app.dataModuleTest

import com.google.common.truth.Truth.assertThat
import com.worldNews.app.data.api.WorldNewsService
import com.worldNews.app.utils.BaseUnitTest
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@HiltAndroidTest
@ExperimentalCoroutinesApi
class WorldNewsRemoteDataSourceImpTest : BaseUnitTest() {


    lateinit var worldNewsAPIService: WorldNewsService
    private lateinit var mockWebServer: MockWebServer


    @Before
    fun setsUp() {
        mockWebServer = MockWebServer()
        worldNewsAPIService = Retrofit.Builder().baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(WorldNewsService::class.java)
    }


    fun enqueueMockUpResponse(fileName: String) {
        val inputStream = javaClass.classLoader?.getResourceAsStream(fileName)
        val source = inputStream?.source()?.buffer()

        val mockResponse = MockResponse()
        mockResponse.setBody(source?.readString(Charsets.UTF_8).toString())
        mockWebServer.enqueue(mockResponse)
    }


    @Test
    fun checkFakeResponseList() {
        runBlocking {
            enqueueMockUpResponse("wn_posts.json")
            val respose = worldNewsAPIService.getWorldNewsApi("us").body()
            val predefineResponse = mockWebServer.takeRequest()
            assertThat(respose).isNotNull()
            assertThat(predefineResponse.path).isEqualTo("/top-headlines?country=us&apiKey=66e93c8c73034ae7beed54f08e915c47")
        }
    }


    @Test
    fun getWorldNewsResponse() {
        runBlocking {
            enqueueMockUpResponse("wn_posts.json")
            val respose = worldNewsAPIService.getWorldNewsApi("us").body()
            assertThat(respose).isNotNull()
             assertThat(respose?.articles?.get(0)?.author).isEqualTo("TMZ Staff")
        }
    }


}