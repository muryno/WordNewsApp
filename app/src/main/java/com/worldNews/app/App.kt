package com.worldNews.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


@HiltAndroidApp
open class App : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        val executorService: ExecutorService =
            Executors.newCachedThreadPool()
        var instance: App? = null
            private set
    }
}