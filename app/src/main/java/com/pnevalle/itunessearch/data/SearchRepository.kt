package com.pnevalle.itunessearch.data

import com.pnevalle.itunessearch.api.SearchService
import com.pnevalle.itunessearch.common.safeApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Repository class for network call, responsible for executing the search API of iTunes
 *
 * @param service the search service class dependency
 */
class SearchRepository @Inject constructor(private val service: SearchService) {
    /**
     * Method implementation for invoking the iTunes Search API
     *
     * @param term the text string you want to search for
     * @param countryCode the two-letter country code for the store you want to search
     * @param media the media type you want to search for
     *
     * @return the flow data stream of [Result] with [SearchResponse] as its value
     */
    fun search(term: String, countryCode: String, media: String): Flow<Result<SearchResponse>> {
        return flow {
            emit(safeApiCall {
                service.searchAppleStore(term, countryCode, media)
            })
        }
    }
}