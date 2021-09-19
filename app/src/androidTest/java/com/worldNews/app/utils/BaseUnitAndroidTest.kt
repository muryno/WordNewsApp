package com.worldNews.app.utils

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule


@HiltAndroidTest
@ExperimentalCoroutinesApi
open class BaseUnitAndroidTest {

    ///InstantTaskExecutorRule allow execution of live data to happen instantly
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @get:Rule
    var hiltRule = HiltAndroidRule(this)


    @Before
    fun setupinit() {
        hiltRule.inject()
    }
}