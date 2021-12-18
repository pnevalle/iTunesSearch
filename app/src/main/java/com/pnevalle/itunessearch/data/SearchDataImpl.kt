package com.pnevalle.itunessearch.data

import javax.inject.Inject

/**
 * The implementation class for [SearchDataSource]
 *
 * @param searchRepository the search repository class dependency
 */
class SearchDataImpl @Inject constructor(
    private val searchRepository: SearchRepository,
    private val lastNetworkCallDataStore: LastNetworkCallDataStore,
) : SearchDataSource {
    override fun search(term: String, countryCode: String, media: String) =
        searchRepository.search(term, countryCode, media)

    override fun getLastSearchTimeStamp() = lastNetworkCallDataStore.lastNetworkCallFlow
}