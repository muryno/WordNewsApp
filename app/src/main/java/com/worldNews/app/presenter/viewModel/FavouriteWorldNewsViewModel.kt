package com.worldNews.app.presenter.viewModel

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.worldNews.app.App
import com.worldNews.app.data.model.Article
import com.worldNews.app.data.util.Resource
import com.worldNews.app.domain.UseCase.GetWorldNewsUseCase
import com.worldNews.app.presenter.adapter.FavouriteWorldNewsAdapter
import com.worldNews.app.presenter.adapter.WorldNewsAdapter
import com.worldNews.app.presenter.di.ToastHelper
import com.worldNews.app.presenter.fragment.DetailsFragment
import com.worldNews.app.presenter.fragment.DetailsFragmentDirections
import com.worldNews.app.utils.NetworkAvailabilityCheckUtils
import com.worldNews.app.utils.safeNavigateFromNavController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class FavouriteWorldNewsViewModel @Inject constructor(
    private val getWorldNewsUseCase: GetWorldNewsUseCase,
    private val toastHelper: ToastHelper,

) : ViewModel() {

    @Inject
    lateinit var adapter: FavouriteWorldNewsAdapter

    var article:  List<Article> = arrayListOf()

    //show empty state
    private val _emptyState: MutableLiveData<Boolean> = MutableLiveData()
    val emptyState: LiveData<Boolean> = _emptyState


    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.IO)


    fun initiateView(){
        coroutineScope.launch {
            withContext(Dispatchers.Main) {
                getWorldNewsUseCase.getLiveFavouriteWorldNnewss().collect {
                    if(it.isNotEmpty()) {
                        adapter.differ.submitList(it)
                        article = it
                    }
                    showEmptyView(it.isEmpty())

                }
            }
        }
    }


    fun addFavourite(article: Article){
        coroutineScope.launch {
            //this shows it is favourite
            article.isFavourite = true
            getWorldNewsUseCase.saveFavouriteWorldNews(article)
        }
        toastHelper.sendToast("News Added to Favourite")
    }


    fun deleteFavourite(article: Article,view: Fragment){
        coroutineScope.launch {
            getWorldNewsUseCase.deleteFavouriteWorldNewsByTitleAndDate(title = article.title?:"",publishedAt = article.publishedAt?:"")
        }
        toastHelper.sendToast("${article.title} delete from your Favourite")
        navigateBackHome(article =  article,view = view)
    }




    fun navigateBackHome(article: Article,view: Fragment){

        val nav = if (article.isFavourite){
            //navigate to favourite
            DetailsFragmentDirections.actionDetailsFragmentToFavouriteFragment()
        }else{
            //navigate to home fragment
            DetailsFragmentDirections.actionDetailsFragmentToHomeFragment()
        }
        view.safeNavigateFromNavController(nav)
    }





     fun showEmptyView(empty: Boolean) {
        if (empty)
            _emptyState.postValue(true)
        else
            _emptyState.postValue(false)
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}





