package com.pnevalle.itunessearch.data.database

import com.pnevalle.itunessearch.data.SearchResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * The implementation class for [SearchResultDaoService]
 *
 * @param searchResultDao the [SearchResultDao] class dependency
 */
class SearchResultDaoServiceImpl @Inject constructor(private val searchResultDao: SearchResultDao) :
    SearchResultDaoService {
    override fun getSearchResults(): Flow<List<SearchResult>> {
        return searchResultDao.getSearchResults()
    }

    override suspend fun insertAll(searchResults: List<SearchResult>) {
        return searchResultDao.insertAll(searchResults)
    }
}