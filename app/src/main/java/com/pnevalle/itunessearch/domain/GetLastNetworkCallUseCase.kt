package com.pnevalle.itunessearch.domain

import com.pnevalle.itunessearch.data.SearchDataSource

/**
 * The get last network call use case, which will return the time in millis for the last
 * successful call
 *
 * @param searchDataSource the data source implementation
 */
class GetLastNetworkCallUseCase(private val searchDataSource: SearchDataSource) {
    operator fun invoke() = searchDataSource.getLastSearchTimeStamp()
}