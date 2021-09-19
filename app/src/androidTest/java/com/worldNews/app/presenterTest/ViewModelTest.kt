package com.worldNews.app.presenterTest

import android.app.Application
import androidx.lifecycle.SavedStateHandle
import androidx.test.filters.MediumTest
import com.worldNews.app.domain.UseCase.GetWorldNewsUseCase
import com.worldNews.app.presenter.viewModel.WorldNewsViewModel
import com.worldNews.app.utils.BaseUnitAndroidTest
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.mockito.Mock

@ExperimentalCoroutinesApi
@MediumTest
@HiltAndroidTest
class ViewModelTest : BaseUnitAndroidTest() {

    @Mock
    private lateinit var appContext: Application
    lateinit var worldNewsViewModel: WorldNewsViewModel


    @Before
    fun setUp() {
        appContext = Application()
        val useCase = GetWorldNewsUseCase(UseCaseRepositoryMock())
        worldNewsViewModel = WorldNewsViewModel(useCase, SavedStateHandle())
    }


}