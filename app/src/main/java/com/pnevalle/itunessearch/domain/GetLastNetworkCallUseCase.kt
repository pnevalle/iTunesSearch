package com.pnevalle.itunessearch.domain

import com.pnevalle.itunessearch.data.SearchDataSource
import javax.inject.Inject

/**
 * The get last network call use case, which will return the time in millis for the last
 * successful call
 *
 * @param searchDataSource the [SearchDataSource] dependency
 */
class GetLastNetworkCallUseCase @Inject constructor(private val searchDataSource: SearchDataSource) {
    operator fun invoke() = searchDataSource.getLastSearchTimeStamp()
}