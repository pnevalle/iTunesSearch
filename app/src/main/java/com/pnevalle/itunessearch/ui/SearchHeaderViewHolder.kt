package com.pnevalle.itunessearch.ui

import android.os.Build
import androidx.recyclerview.widget.RecyclerView
import com.pnevalle.itunessearch.databinding.ItemSearchHeadingBinding
import java.text.SimpleDateFormat
import java.util.*

/**
 * The list header view holder, which is the time stamp of the last network call
 */
class SearchHeaderViewHolder(private val binding: ItemSearchHeadingBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        const val DATE_FORMAT = "EEE, MMM dd hh:mm a"
    }

    /**
     * Bind the heading text to UI
     *
     * @param timestamp the last network call timestamp
     */
    fun bind(timestamp: Long?) {
        val heading: String = if (timestamp == null) {
            "N/A"
        } else {
            val locale = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                itemView.resources.configuration.locales[0]
            } else {
                itemView.resources.configuration.locale
            }
            val displayDateFormat = SimpleDateFormat(DATE_FORMAT, locale)
            displayDateFormat.format(Date(timestamp))
        }

        binding.heading = heading

        binding.executePendingBindings()
    }
}