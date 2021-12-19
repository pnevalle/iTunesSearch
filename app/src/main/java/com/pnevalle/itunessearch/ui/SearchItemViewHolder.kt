package com.pnevalle.itunessearch.ui

import androidx.recyclerview.widget.RecyclerView
import com.pnevalle.itunessearch.common.getPriceDisplay
import com.pnevalle.itunessearch.data.SearchResult
import com.pnevalle.itunessearch.databinding.ItemSearchBinding
import java.util.*

/**
 * The search item view holder, which represents an item in the list
 */
class SearchItemViewHolder(private val binding: ItemSearchBinding) :
    RecyclerView.ViewHolder(binding.root) {

    /**
     * Bind the search result data to UI
     */
    fun bind(searchResult: SearchResult) {
        binding.apply {
            trackId = searchResult.trackId
            trackName = searchResult.trackName
            genre = searchResult.primaryGenreName
            price = getPriceDisplay(searchResult.currency, searchResult.trackPrice)
            url = searchResult.imageUrl
            executePendingBindings()
        }
    }
}