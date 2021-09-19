package com.worldNews.app.presenterTest

import android.app.Application
import androidx.test.filters.MediumTest
import com.worldNews.app.domain.UseCase.GetWorldNewsUseCase
import com.worldNews.app.presenter.viewModel.FavouriteWorldNewsViewModel
import com.worldNews.app.utils.BaseUnitAndroidTest
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.mockito.Mock


@ExperimentalCoroutinesApi
@MediumTest
@HiltAndroidTest
class FavouriteWorldNewsViewModelTest:  BaseUnitAndroidTest()  {

    @Mock
    private lateinit var appContext: Application
    lateinit var worldNewsViewModel: FavouriteWorldNewsViewModel

    @Before
    fun setUp() {
        appContext = Application()
        val useCase = GetWorldNewsUseCase(UseCaseRepositoryMock())
        worldNewsViewModel = FavouriteWorldNewsViewModel( useCase)
    }



}