package com.pnevalle.itunessearch.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
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

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLiveDataObserver()

        viewModel.checkCacheOrSearch()
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
            val adapter = SearchAdapter {
                val direction =
                    SearchListFragmentDirections.actionSearchListFragmentToSearchDetailFragment(
                        it.trackId)
                findNavController().navigate(direction)
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