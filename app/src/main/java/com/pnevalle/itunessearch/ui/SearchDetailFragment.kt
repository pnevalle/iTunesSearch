package com.pnevalle.itunessearch.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.pnevalle.itunessearch.R
import com.pnevalle.itunessearch.common.getPriceDisplay
import com.pnevalle.itunessearch.databinding.FragmentSearchDetailBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception

/**
 * The detail screen for [SearchListFragment]
 */
@AndroidEntryPoint
class SearchDetailFragment : Fragment() {

    private var _binding: FragmentSearchDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchViewModel by activityViewModels()
    private val args: SearchDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postponeEnterTransition()
        sharedElementEnterTransition = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSearchDetailBinding.inflate(inflater, container, false)

        initViews()

        return binding.root
    }

    private fun initViews() {
        val searchResult = viewModel.getSearchResult(args.trackId)

        searchResult?.let { result ->
            binding.apply {
                trackId = result.trackId
                trackName = result.trackName
                genre = result.primaryGenreName
                price = getPriceDisplay(searchResult.currency, searchResult.trackPrice)
                description = result.longDescription
                url = result.imageUrl

                executePendingBindings()

                Picasso.get()
                    .load(url)
                    .noFade()
                    .placeholder(R.drawable.bg_image_placeholder)
                    .error(R.drawable.bg_image_placeholder)
                    .into(ivSearchImage, object: Callback {
                        override fun onSuccess() {
                            startPostponedEnterTransition()
                        }

                        override fun onError(e: Exception?) {
                            startPostponedEnterTransition()

                        }
                    })
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}