package com.pnevalle.itunessearch.data

import com.google.gson.annotations.SerializedName

/**
 * Model for the search result in [SearchResponse]
 *
 * @param trackId the track id
 * @param trackName the name of the track, song, video, TV episode, and so on returned by the search request.
 * @param imageUrl the URL for the artwork associated, sized to 100x100 pixels
 * @param longDescription the long description of the track, song, video, TV episode, etc.
 * @param primaryGenreName the primary genre of the track, song, video, TV episode, etc.
 * @param trackPrice the track price
 */
data class SearchResult(
    val trackId: Long,
    val trackName: String,
    @SerializedName("artworkUrl100") val imageUrl: String,
    val longDescription: String,
    val primaryGenreName: String,
    val trackPrice: Double,
)