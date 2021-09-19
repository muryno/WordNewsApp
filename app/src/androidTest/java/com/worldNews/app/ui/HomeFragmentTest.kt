package com.worldNews.app.ui

import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import androidx.test.platform.app.InstrumentationRegistry
import com.worldNews.app.R
import com.worldNews.app.presenter.fragment.HomeFragment
import com.worldNews.app.utils.BaseUnitAndroidTest
import com.worldNews.app.utils.launchFragmentInHiltContainer
import com.google.common.truth.Truth
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@MediumTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class HomeFragmentTest: BaseUnitAndroidTest() {


    private lateinit var stringToBetyped: String



    val navController = TestNavHostController(ApplicationProvider.getApplicationContext())


    @Before
    fun initValidString() {
        // Specify a valid string.
        stringToBetyped = "product"
    }

    @Test
    fun displayScreenTitle() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.carbonWeightwatchers.cww", appContext.packageName)
    }




    @Test
    fun givenWeightWatcherClickedThenNavigateToDetailsScreen() {
        //Launch fragment
        launchCWWListFragment()

        //Click on first article
        onView(withId(R.id.cww_recyclerView)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
        //Check that it navigates to Detail screen
        Truth.assertThat(navController.currentDestination?.id).isEqualTo(
            R.id.detailsFragment
        )

    }


    private fun launchCWWListFragment() {
        launchFragmentInHiltContainer<HomeFragment> {
            navController.setGraph(R.navigation.nav_host)
            navController.setCurrentDestination(R.id.homeFragment)
            this.viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
                if (viewLifecycleOwner != null) {
                    // The fragment’s view has just been created
                    Navigation.setViewNavController(this.requireView(), navController)
                }
            }
        }
    }



}