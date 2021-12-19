package com.pnevalle.itunessearch.domain.database

import com.pnevalle.itunessearch.data.database.SearchResultDaoService
import javax.inject.Inject

/**
 * The get all search result cache use case, which will retrieve all the search results saved from
 * [InsertSearchResultCacheUseCase]
 *
 * @param searchDataDaoService the [SearchResultDaoService] dependency
 */
class GetAllSearchResultCacheUseCase @Inject constructor(private val searchDataDaoService: SearchResultDaoService) {
    operator fun invoke() = searchDataDaoService.getSearchResults()
}