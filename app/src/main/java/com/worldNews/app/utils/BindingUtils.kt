package com.worldNews.app.utils

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.worldNews.app.R
import com.worldNews.app.data.model.Article
import com.worldNews.app.presenter.viewModel.FavouriteWorldNewsViewModel
import com.worldNews.app.presenter.viewModel.WorldNewsViewModel

@BindingAdapter("imagePaths", "pathError", "imageOption")
fun loadImage(imageView: ImageView, @Nullable path:String?, errorDrawable: Drawable, option:String) {
    var myOptions = RequestOptions()
        .placeholder(errorDrawable)
        .error(errorDrawable)


    when(option) {
        "fit" -> myOptions = myOptions.fitCenter()
        "inside" -> myOptions = myOptions.centerInside()
        "crop" -> myOptions = myOptions.centerCrop()
    }

    Glide.with(imageView.context)
        .load(path ?: "")
        .apply(myOptions)
        .into(imageView)
}


@BindingAdapter("addTextChangeListener")
fun addTextChangeListener(view: SearchView, @Nullable favouriteWorldNewsViewModel: FavouriteWorldNewsViewModel?) {

    view.setOnQueryTextListener(
        object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                if (p0?.isNotEmpty() == true)
                    favouriteWorldNewsViewModel?.apply {
                        article.filter { it.title?.lowercase()?.contains(p0.lowercase()) == true }
                            .let { tt -> adapter.differ.submitList(tt)
                            showEmptyView(article.isEmpty())
                    } }
                else
                    favouriteWorldNewsViewModel?.initiateView()
                return false
            }
        }
    )

    view.setOnCloseListener {
        favouriteWorldNewsViewModel?.initiateView()
        false
    }

}


@BindingAdapter("dynamicIcon")
fun View.setDynamicIcon(isFavourite: Article) {
    val floatingButton = (this as FloatingActionButton)
    if(isFavourite.isFavourite) {
        floatingButton.setImageResource(R.drawable.remove)
        floatingButton.backgroundTintList = ColorStateList.valueOf(Color.RED)
    }
    else     {
        floatingButton.setImageResource(R.drawable.add)
        floatingButton.backgroundTintList = ColorStateList.valueOf(Color.GREEN)

    }
}




@BindingAdapter("date")
fun TextView.convertDate(rawDate:String?) {
    text = rawDate?.let {
        rawDate.substring(0,4) + "/" + rawDate.substring(4,6) + "/" + rawDate.substring(6)
    } ?: ""
}

@BindingConversion
fun convertBooleanToVisibility(visible:Boolean) :Int {
    return if(visible) View.VISIBLE else View.GONE
}
