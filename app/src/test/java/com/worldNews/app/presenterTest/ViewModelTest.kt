package com.worldNews.app.presenterTest

import android.app.Application
import androidx.lifecycle.SavedStateHandle
import com.worldNews.app.domain.UseCase.GetWorldNewsUseCase
import com.worldNews.app.presenter.di.ToastHelper
import com.worldNews.app.presenter.viewModel.WorldNewsViewModel
import com.worldNews.app.utils.BaseUnitTest
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.mockito.Mock

@HiltAndroidTest
@ExperimentalCoroutinesApi
class ViewModelTest : BaseUnitTest() {

    @Mock
    private lateinit var appContext: Application
    lateinit var worldNewsViewModel: WorldNewsViewModel


    @Before
    fun setUp() {
        appContext = Application()
        worldNewsViewModel = WorldNewsViewModel(  GetWorldNewsUseCase(UseCaseRepositoryMock()),ToastHelper(),SavedStateHandle())
    }


}