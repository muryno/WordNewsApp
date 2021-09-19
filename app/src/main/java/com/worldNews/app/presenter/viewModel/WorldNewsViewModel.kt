package com.worldNews.app.presenter.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.worldNews.app.App
import com.worldNews.app.data.model.Article
import com.worldNews.app.data.util.Resource
import com.worldNews.app.domain.UseCase.GetWorldNewsUseCase
import com.worldNews.app.presenter.adapter.WorldNewsAdapter
import com.worldNews.app.presenter.di.ToastHelper
import com.worldNews.app.utils.NetworkAvailabilityCheckUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class WorldNewsViewModel @Inject constructor(
    private val getWorldNewsUseCase: GetWorldNewsUseCase,
    private val toastHelper: ToastHelper,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    @Inject
    lateinit var adapter: WorldNewsAdapter

    //for search
     var worldNewsItem: List<Article> = arrayListOf()

    //initial loading
    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> = _loading

    //failed
    val failure: MutableLiveData<Boolean> = MutableLiveData()

    //show empty state
    private val _emptyState: MutableLiveData<Boolean> = MutableLiveData()
    val emptyState: LiveData<Boolean> = _emptyState


    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.IO)


    fun onCreate() {
        _loading.value = false
        failure.value = false
        getWorldNews("us")
    }



    fun getWorldNews(country: String) {
        if (NetworkAvailabilityCheckUtils.isNetworkAvailable(App.instance?.applicationContext)>0) {
            coroutineScope.launch {
                try {
                    getWorldNewsUseCase.fetchWorldNewsFromApi(country).collect {
                        when (it) {
                            is Resource.Loading -> {
                               // if it is empty, load else don't
                                _loading.postValue(worldNewsItem.isEmpty())
                            }
                            is Resource.Success -> {
                                withContext(Dispatchers.Main) {
                                    if (it.data.isNotEmpty()) {
                                        updateView(it.data)
                                    }
                                }
                            }
                            is Resource.Error -> {
                                withContext(Dispatchers.Main) {
                                    _loading.postValue(false)
                                    failure.postValue(true)
                                    toastHelper.sendToast(it?.error?.message ?: "error occur")
                                }
                            }
                        }
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main)
                    {
                        toastHelper.sendToast(e.message.toString())
                    }
                }
            }
        } else {

                toastHelper.sendToast("Internet is not available")
        }
    }


    private fun showEmptyView(empty: Boolean) {
        if (empty)
            _emptyState.postValue(true)
        else
            _emptyState.postValue(false)
    }

    private fun updateView(article :List<Article>){
        worldNewsItem = article
        //save to db

        if(article.isNotEmpty()) {
            adapter.differ.submitList(article)
            //   showEmptyView(it.isEmpty())
        }
        _loading.postValue(false)
        failure.postValue(false)
    }



    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}





