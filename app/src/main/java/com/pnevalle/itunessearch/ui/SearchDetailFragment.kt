package com.pnevalle.itunessearch.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.pnevalle.itunessearch.databinding.FragmentSearchDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

/**
 * The detail screen for [SearchListFragment]
 */
@AndroidEntryPoint
class SearchDetailFragment : Fragment() {

    private var _binding: FragmentSearchDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchViewModel by activityViewModels()
    private val args: SearchDetailFragmentArgs by navArgs()

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
                trackName = result.trackName
                genre = result.primaryGenreName
                price = "${Currency.getInstance(result.currency).symbol} ${result.trackPrice}"
                url = result.imageUrl
                description = result.longDescription

                executePendingBindings()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}