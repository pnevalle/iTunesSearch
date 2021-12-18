package com.pnevalle.itunessearch.domain

import com.pnevalle.itunessearch.common.DEFAULT_SEARCH_COUNTRY
import com.pnevalle.itunessearch.common.DEFAULT_SEARCH_MEDIA
import com.pnevalle.itunessearch.common.DEFAULT_SEARCH_TERM
import com.pnevalle.itunessearch.data.SearchDataSource

/**
 * The search apple store use case, which will execute the API call
 *
 * @param searchDataSource the data source implementation
 */
class SearchAppleStoreUseCase(private val searchDataSource: SearchDataSource) {

    /**
     * Invoke method to execute the search API
     *
     * @param term the text string you want to search for
     * @param countryCode the two-letter country code for the store you want to search
     * @param media the media type you want to search for
     */
    operator fun invoke(
        term: String = DEFAULT_SEARCH_TERM,
        countryCode: String = DEFAULT_SEARCH_COUNTRY,
        media: String = DEFAULT_SEARCH_MEDIA,
    ) = searchDataSource.search(term, countryCode, media)
}