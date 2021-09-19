package com.worldNews.app.utils

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule


@HiltAndroidTest
@ExperimentalCoroutinesApi
open class BaseUnitTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()


    ///InstantTaskExecutorRule allow execution of live data to happen instantly
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


}