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
import com.worldNews.app.utils.CurrentDate
import com.worldNews.app.utils.NetworkAvailabilityCheckUtils
import com.worldNews.app.utils.ShowToast
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class WorldNewsViewModel @Inject constructor(
    private val getWorldNewsUseCase: GetWorldNewsUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    @Inject
    lateinit var adapter: WorldNewsAdapter




    //initial loading
    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> = _loading

    //initial loading
    private val _currentDate: MutableLiveData<String> = MutableLiveData()
    val currentDate: LiveData<String> = _currentDate


    //failed
    val failure: MutableLiveData<Boolean> = MutableLiveData()

    //show empty state
    private val _emptyState: MutableLiveData<Boolean> = MutableLiveData()
    val emptyState: LiveData<Boolean> = _emptyState


    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.IO)


    init {
        onCreate()
    }
    fun onCreate() {
        _loading.value = false
        failure.value = false

        getWorldNews("us")

        //current date
        _currentDate.value = CurrentDate()
    }



    fun refresh(){
        _loading.postValue(true)
        getWorldNews("us")
        showEmptyView(false)
    }


    //get world news from api and check if network iss avilable or not
    fun getWorldNews(country: String) {
        if (NetworkAvailabilityCheckUtils.isNetworkAvailable(App.instance?.applicationContext)>0) {
            coroutineScope.launch {
                try {
                    getWorldNewsUseCase.fetchWorldNewsFromApi(country).collect {
                        when (it) {
                            is Resource.Loading -> {
                               // if it is empty, load else don't
                                _loading.postValue(true)
                            }
                            is Resource.Success -> {
                                withContext(Dispatchers.Main) {
                                    if (it.data.isNotEmpty()) {
                                        updateView(it.data)
                                    }
                                    showEmptyView(it.data.isEmpty())
                                }
                            }
                            is Resource.Error -> {
                                withContext(Dispatchers.Main) {
                                    _loading.postValue(false)
                                    failure.postValue(true)
                                    ShowToast(it?.error?.message ?: "error occur")
                                    showEmptyView(true)
                                }
                            }
                        }
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main)
                    {
                        ShowToast(e.message.toString())
                        showEmptyView(true)
                    }
                }
            }
        } else {

            ShowToast("Internet is not available")
        }
    }


    //decide showing refresh empty box layout
    private fun showEmptyView(empty: Boolean) {
        if (empty)
            _emptyState.postValue(true)
        else
            _emptyState.postValue(false)
    }


    private fun updateView(article :List<Article>){
        if(article.isNotEmpty()) {
            adapter.differ.submitList(article)
        }
        showEmptyView(article.isEmpty())
        _loading.postValue(false)
        failure.postValue(false)
    }



    //to prevent memory leakage
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}





