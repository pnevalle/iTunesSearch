package com.pnevalle.itunessearch.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pnevalle.itunessearch.data.SearchResult
import com.pnevalle.itunessearch.databinding.ItemSearchBinding
import com.pnevalle.itunessearch.databinding.ItemSearchHeadingBinding

/**
 * The adapter class for [SearchListFragment]
 *
 * @param clickListener the item click listener
 */
class SearchAdapter(
    private val clickListener: (SearchResult) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var searchResultList: List<SearchResult> = listOf()
    private var lastNetworkCall: Long? = null

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

            binding.viewHolder = viewHolder
            binding.adapter = this

            viewHolder
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SearchItemViewHolder -> {
                holder.bind(searchResultList[position - 1])
            }

            is SearchHeaderViewHolder -> {
                holder.bind(lastNetworkCall ?: System.currentTimeMillis())
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

    fun onItemClick(position: Int) {
        clickListener(searchResultList[position-1])
    }

    /**
     * Set the data list of the adapter
     *
     * @param searchResultList the list of [SearchResult]
     */
    @SuppressLint("NotifyDataSetChanged")
    fun setDataList(searchResultList: List<SearchResult>) {
        this.searchResultList = searchResultList.toList()
        notifyDataSetChanged()
    }

    /**
     * Set the last network call and notify item change
     *
     * @param lastNetworkCall the last network call in millis
     */
    fun setLastNetworkCall(lastNetworkCall: Long?) {
        this.lastNetworkCall = lastNetworkCall
        if (searchResultList.isNotEmpty()) {
            notifyItemChanged(0)
        }
    }
}