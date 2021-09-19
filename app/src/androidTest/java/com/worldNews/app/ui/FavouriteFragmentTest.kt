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
import com.google.common.truth.Truth
import com.worldNews.app.R
import com.worldNews.app.presenter.fragment.HomeFragment
import com.worldNews.app.utils.BaseUnitAndroidTest
import com.worldNews.app.utils.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith


@MediumTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class FavouriteFragmentTest: BaseUnitAndroidTest() {

    private val navController = TestNavHostController(ApplicationProvider.getApplicationContext())


    @Test
    fun givenWeightWatcherClickedThenNavigateToDetailsScreen() {
        //Launch fragment
        launchHomeListFragment()

        //Click on first article
        onView(withId(R.id.cww_recyclerView)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
        //Check that it navigates to Detail screen
        Truth.assertThat(navController.currentDestination?.id).isEqualTo(
            R.id.detailsFragment
        )

    }


    private fun launchHomeListFragment() {
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