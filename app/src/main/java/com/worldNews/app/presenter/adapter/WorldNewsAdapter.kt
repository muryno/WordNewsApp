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
import com.worldNews.app.databinding.ListItemBinding
import com.worldNews.app.presenter.fragment.HomeFragmentDirections
import com.worldNews.app.presenter.interfaces.OnRecyclerItemClickListener
import javax.inject.Inject


class WorldNewsAdapter @Inject constructor() :
    RecyclerView.Adapter<WorldNewsAdapter.MyViewHolder>() {


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
        val binding: ListItemBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.list_item,
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


    inner class MyViewHolder(val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(article: Article?) {
            binding.title.text = article?.title
            binding.description.text = article?.description
            binding.time.text = article?.publishedAt

            Glide.with(binding.imageView.context)
                .load(article?.urlToImage)
                .placeholder(R.drawable.ic_baseline_image_24)
                .into(binding.imageView)


            binding.cardView.setOnClickListener {
                if (article != null) {
                    val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(article)
                    Navigation.findNavController(binding.root).navigate(action)
                }
            }


        }
    }

}