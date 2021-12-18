package com.pnevalle.itunessearch.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pnevalle.itunessearch.data.SearchResult
import com.pnevalle.itunessearch.databinding.ItemSearchBinding
import com.pnevalle.itunessearch.databinding.ItemSearchHeadingBinding

/**
 * The adapter class for [SearchListFragment]
 */
class SearchAdapter(
    private val searchResultList: List<SearchResult>,
    private val lastNetworkCall: Long,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val HEADER_TYPE = 100
        const val ITEM_TYPE = 101
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == HEADER_TYPE) {
            val binding = ItemSearchHeadingBinding.inflate(inflater, parent, false)
            SearchHeaderViewHolder(binding)
        } else {
            val binding = ItemSearchBinding.inflate(inflater, parent, false)
            val viewHolder = SearchItemViewHolder(binding)

            viewHolder
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SearchItemViewHolder -> {
                holder.bind(searchResultList[position - 1])
            }

            is SearchHeaderViewHolder -> {
                holder.bind(lastNetworkCall)
            }
        }
    }

    override fun getItemCount(): Int = searchResultList.size + 1

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            HEADER_TYPE
        } else {
            ITEM_TYPE
        }
    }
}