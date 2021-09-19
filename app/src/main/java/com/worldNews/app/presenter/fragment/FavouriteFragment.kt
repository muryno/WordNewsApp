package com.worldNews.app.presenter.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.worldNews.app.R
import com.worldNews.app.databinding.FragmentFavouriteBinding
import com.worldNews.app.databinding.FragmentHomeBinding
import com.worldNews.app.presenter.viewModel.FavouriteWorldNewsViewModel
import com.worldNews.app.presenter.viewModel.WorldNewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteFragment : Fragment() {

    val favouriteWorldNewsViewModel by viewModels<FavouriteWorldNewsViewModel>()

    lateinit var binding: FragmentFavouriteBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFavouriteBinding.inflate(inflater, container, false).apply {
            viewModel = favouriteWorldNewsViewModel
            lifecycleOwner = this@FavouriteFragment
        }
        favouriteWorldNewsViewModel.initiateView()
        return binding.root
    }

}