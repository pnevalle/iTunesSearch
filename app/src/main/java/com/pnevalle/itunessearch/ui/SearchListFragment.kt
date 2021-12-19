package com.pnevalle.itunessearch.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.pnevalle.itunessearch.databinding.FragmentSearchListBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * The search list fragment UI
 */
@AndroidEntryPoint
class SearchListFragment : Fragment() {
    private var _binding: FragmentSearchListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSearchListBinding.inflate(inflater, container, false)

        initViews()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLiveDataObserver()

        viewModel.checkCacheOrSearch()
    }

    private fun initViews() {
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            viewModel = this@SearchListFragment.viewModel
            lifecycleOwner = this@SearchListFragment
            recyclerView.viewTreeObserver.addOnPreDrawListener(object: ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    startPostponedEnterTransition()
                    binding.recyclerView.viewTreeObserver.removeOnPreDrawListener(this)
                    return true
                }
            })
        }
        postponeEnterTransition()
    }

    /**
     * Initialize live data observer
     */
    private fun initLiveDataObserver() {
        viewModel.lastNetworkCall.observe(viewLifecycleOwner) {
            getAdapter().setLastNetworkCall(it)
        }

        viewModel.searchResultList.observe(viewLifecycleOwner) { searchResults ->
            getAdapter().setDataList(searchResults)
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { errorEvent ->
            errorEvent.getContentIfNotHandled()?.let { errorMessage ->
                showSnack(errorMessage)
            }
        }
    }

    private fun getAdapter(): SearchAdapter {
        return if (binding.recyclerView.adapter == null) {
            val adapter =
                SearchAdapter { searchResult, imageView, tvTrackName, tvTrackGenre, tvPrice ->
                    val direction =
                        SearchListFragmentDirections.actionSearchListFragmentToSearchDetailFragment(
                            searchResult.trackId)
                    val extras = FragmentNavigatorExtras(
                        imageView to searchResult.imageUrl,
                        tvTrackName to searchResult.trackName,
                        tvTrackGenre to "${searchResult.trackId} genre",
                        tvPrice to "${searchResult.trackId} price",
                    )
                    findNavController().navigate(direction, extras)
                }
            binding.recyclerView.adapter = adapter
            adapter
        } else {
            binding.recyclerView.adapter as SearchAdapter
        }
    }

    private fun showSnack(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}