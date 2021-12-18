package com.pnevalle.itunessearch.data

import kotlinx.coroutines.flow.Flow

/**
 * The abstraction for the search feature. Contains the methods needed for the search.
 */
interface SearchDataSource {
    /**
     * Method to implement for invoking the iTunes Search API
     *
     * @param term the text string you want to search for
     * @param countryCode the two-letter country code for the store you want to search
     * @param media the media type you want to search for
     *
     * @return the flow data stream of [Result] with [SearchResponse] as its value
     */
    fun search(term: String, countryCode: String, media: String): Flow<Result<SearchResponse>>
}