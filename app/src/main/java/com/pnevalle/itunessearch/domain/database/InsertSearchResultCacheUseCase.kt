package com.pnevalle.itunessearch.domain.database

import com.pnevalle.itunessearch.data.SearchResult
import com.pnevalle.itunessearch.data.database.SearchResultDaoService
import javax.inject.Inject

/**
 * The insert search result cache use case, which will insert the search results into the room
 * database
 *
 * @param searchDataDaoService the [SearchResultDaoService] dependency
 */
class InsertSearchResultCacheUseCase @Inject constructor(private val searchDataDaoService: SearchResultDaoService) {
    suspend operator fun invoke(searchResults: List<SearchResult>) =
        searchDataDaoService.insertAll(searchResults)
}