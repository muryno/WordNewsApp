package com.worldNews.app.ui

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.worldNews.app.FakeCarbonWeightWatcherData
import com.worldNews.app.R
import com.worldNews.app.data.model.Article
import com.worldNews.app.presenter.fragment.DetailsFragment
import com.worldNews.app.presenter.fragment.DetailsFragmentArgs
import com.worldNews.app.utils.BaseUnitAndroidTest
import com.worldNews.app.utils.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@MediumTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class DetailsFragmentTest  : BaseUnitAndroidTest() {


    private var carbonWeightWatcherItem: Article = FakeCarbonWeightWatcherData.fakeArticleResponse[0]

    @Before
    fun setupTest() {

    }

    @Test
    fun detailsFragmentTestDisplayedInUi() {

         //WHEN - Details fragment launched to display task
        val bundle = DetailsFragmentArgs(carbonWeightWatcherItem).toBundle()
        launchFragmentInHiltContainer<DetailsFragment>(bundle, R.style.AppTheme)

        Espresso.onView(withId(R.id.img))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(withId(R.id.backBtn))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(withId(R.id.details_title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(withId(R.id.details_author))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(withId(R.id.details_publish))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Thread.sleep(3000)
    }

}