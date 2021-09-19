package com.worldNews.app.presenter.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.navArgs
import com.worldNews.app.databinding.FragmentDetailsBinding
import com.worldNews.app.presenter.viewModel.FavouriteWorldNewsViewModel
import com.worldNews.app.utils.safeNavigateFromNavController
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    val favouriteWorldNewsViewModel by viewModels<FavouriteWorldNewsViewModel>()

    lateinit var nav : NavDirections
    var binding: FragmentDetailsBinding? = null
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@DetailsFragment
            worldNewsArticleModelItem = args.article
            viewModel = favouriteWorldNewsViewModel
            backBtn.setOnClickListener {

                nav = if (args.article.isFavourite){
                    //navigate to favourite
                    DetailsFragmentDirections.actionDetailsFragmentToFavouriteFragment()
                }else{
                    //navigate to home fragment
                    DetailsFragmentDirections.actionDetailsFragmentToHomeFragment()
                }

                this@DetailsFragment.safeNavigateFromNavController(nav)
            }


        }


        return binding?.root
    }

}


