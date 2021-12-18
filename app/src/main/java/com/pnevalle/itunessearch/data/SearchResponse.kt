package com.pnevalle.itunessearch.data

/**
 * The API server response model
 *
 * @param resultCount the result count
 * @param results the list of [SearchResult]
 */
data class SearchResponse(
    val resultCount: Int,
    val results: List<SearchResult>,
)