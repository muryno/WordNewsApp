package com.worldNews.app.presenter.viewModel

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.worldNews.app.data.model.Article
import com.worldNews.app.domain.UseCase.GetWorldNewsUseCase
import com.worldNews.app.presenter.adapter.FavouriteWorldNewsAdapter
import com.worldNews.app.presenter.fragment.DetailsFragmentDirections
import com.worldNews.app.utils.ShowToast
import com.worldNews.app.utils.safeNavigateFromNavController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class FavouriteWorldNewsViewModel @Inject constructor(
    private val getWorldNewsUseCase: GetWorldNewsUseCase,
    ) : ViewModel() {

    @Inject
    lateinit var adapter: FavouriteWorldNewsAdapter

    var article:  List<Article> = arrayListOf()

    //show empty state
    private val _emptyState: MutableLiveData<Boolean> = MutableLiveData()
    val emptyState: LiveData<Boolean> = _emptyState


    //hide fab button when  clicked
    private val _hideFab: MutableLiveData<Boolean> = MutableLiveData()
    val hideFab: LiveData<Boolean> = _hideFab


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

        _hideFab.value = false
    }


    fun addFavourite(article: Article){
      coroutineScope.launch {
            //this shows it is favourite
            article.isFavourite = true
            getWorldNewsUseCase.saveFavouriteWorldNews(article)
        }
        ShowToast("News Added to Favourite")
        _hideFab.value = true
    }


    fun deleteFavourite(article: Article,view: Fragment){
        coroutineScope.launch {
            getWorldNewsUseCase.deleteFavouriteWorldNewsByTitleAndDate(title = article.title?:"",publishedAt = article.publishedAt?:"")
        }
        ShowToast("${article.title} delete from your Favourite")
        navigateBackHome(article =  article,view = view)
    }


    //for detail screen to navigate back
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





