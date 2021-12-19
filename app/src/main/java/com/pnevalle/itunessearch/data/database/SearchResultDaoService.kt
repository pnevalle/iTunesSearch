package com.pnevalle.itunessearch.data.database

import com.pnevalle.itunessearch.data.SearchResult
import kotlinx.coroutines.flow.Flow

/**
 * The abstraction for the search result room database feature
 */
interface SearchResultDaoService {
    /**
     * Implement the method to retrieve the list of [SearchResult] in the database
     * @return [Flow] of the list of [SearchResult]
     */
    fun getSearchResults(): Flow<List<SearchResult>>

    /**
     * Implement the method to insert the list of [SearchResult] into the database
     * @param searchResults the list of [SearchResult] to insert
     */
    suspend fun insertAll(searchResults: List<SearchResult>)
}