package com.worldNews.app.presenter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.worldNews.app.BuildConfig
import com.worldNews.app.R
import com.worldNews.app.data.model.Article
import com.worldNews.app.databinding.FavouriteListItemBinding
import com.worldNews.app.databinding.ListItemBinding
import com.worldNews.app.presenter.fragment.FavouriteFragmentDirections
import com.worldNews.app.presenter.fragment.HomeFragmentDirections
import com.worldNews.app.presenter.interfaces.OnRecyclerItemClickListener
import javax.inject.Inject


class FavouriteWorldNewsAdapter @Inject constructor() :
    RecyclerView.Adapter<FavouriteWorldNewsAdapter.MyViewHolder>() {


    private val callback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(
            oldItemNews: Article,
            newItemNews: Article
        ): Boolean {
            return oldItemNews.publishedAt == newItemNews.publishedAt
        }

        override fun areContentsTheSame(
            oldItemNews: Article,
            newItemNews: Article
        ): Boolean {
            return oldItemNews == newItemNews
        }

    }

    val differ = AsyncListDiffer(this, callback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: FavouriteListItemBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.favourite_list_item,
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val worldNewsItem= differ.currentList[position]
        holder.bind(worldNewsItem)
    }


    inner class MyViewHolder(val binding: FavouriteListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article?) {
            binding.fvTitle.text = article?.title
            binding.fvDescription.text = article?.description
            binding.fvTime.text = article?.publishedAt

            Glide.with(binding.fvImageView.context)
                .load(article?.urlToImage)
                .placeholder(R.drawable.ic_baseline_image_24)
                .into(binding.fvImageView)


            binding.cardView.setOnClickListener {
                if (article != null) {
                    val action = FavouriteFragmentDirections.actionFavouriteFragmentToDetailsFragment(article)
                    Navigation.findNavController(binding.root).navigate(action)
                }
            }


        }
    }

}