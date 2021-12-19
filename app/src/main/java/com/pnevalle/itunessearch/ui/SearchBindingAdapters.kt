package com.pnevalle.itunessearch.ui

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.pnevalle.itunessearch.R
import com.squareup.picasso.Picasso

/**
 * Bind the swipe to refresh layout listener
 */
@BindingAdapter("on_swipe_refresh")
fun bindOnSwipeRefreshLayout(swipeRefreshLayout: SwipeRefreshLayout, callback: () -> Unit) {
    swipeRefreshLayout.setOnRefreshListener(callback)
}

/**
 * Set the refreshing state if the [SwipeRefreshLayout]
 */
@BindingAdapter("is_refreshing")
fun bindOnSwipeRefreshLayout(swipeRefreshLayout: SwipeRefreshLayout, isRefreshing: Boolean) {
    swipeRefreshLayout.isRefreshing = isRefreshing
}

/**
 * Bind the image url to the [ImageView]
 */
@BindingAdapter("url")
fun bindImageUrl(imageView: ImageView, url: String) {
    Picasso.get()
        .load(url)
        .placeholder(R.drawable.bg_image_placeholder)
        .error(R.drawable.bg_image_placeholder)
        .into(imageView)
}

/**
 * Bind the transition name of the textview
 */
@BindingAdapter("tv_transition_name")
fun bindTextViewTransitionName(textView: TextView, transitionName: String) {
    textView.transitionName = transitionName
}

/**
 * Bind the transition name of the image view
 */
@BindingAdapter("iv_transition_name")
fun bindTextViewTransitionName(imageView: ImageView, transitionName: String) {
    imageView.transitionName = transitionName
}

