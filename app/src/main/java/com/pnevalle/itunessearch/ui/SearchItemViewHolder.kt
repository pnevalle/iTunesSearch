package com.pnevalle.itunessearch.ui

import androidx.recyclerview.widget.RecyclerView
import com.pnevalle.itunessearch.data.SearchResult
import com.pnevalle.itunessearch.databinding.ItemSearchBinding

/**
 * The search item view holder, which represents an item in the list
 */
class SearchItemViewHolder(private val binding: ItemSearchBinding) :
    RecyclerView.ViewHolder(binding.root) {

    /**
     * Bind the search result data to UI
     */
    fun bind(searchResult: SearchResult) {
        binding.trackName = searchResult.trackName
        binding.genre = searchResult.primaryGenreName
        binding.price = "${searchResult.trackPrice} ${searchResult.currency}"

        binding.executePendingBindings()
    }
}