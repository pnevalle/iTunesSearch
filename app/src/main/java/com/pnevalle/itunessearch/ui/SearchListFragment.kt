package com.pnevalle.itunessearch.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
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

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLiveDataObserver()

        viewModel.search()
    }

    /**
     * Initialize live data observer
     */
    private fun initLiveDataObserver() {
        viewModel.searchResultList.observe(viewLifecycleOwner) { searchResults ->
            binding.swipeRefreshLayout.isRefreshing = false
            binding.recyclerView.adapter = SearchAdapter(searchResults,
                viewModel.lastNetworkCall.value ?: System.currentTimeMillis())
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { errorEvent ->
            binding.swipeRefreshLayout.isRefreshing = false
            errorEvent.getContentIfNotHandled()?.let { errorMessage ->
                showSnack(errorMessage)
            }
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