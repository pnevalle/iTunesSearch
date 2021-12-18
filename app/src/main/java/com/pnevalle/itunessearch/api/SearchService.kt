package com.pnevalle.itunessearch.api

import com.pnevalle.itunessearch.data.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * The iTunes search API endpoint
 */
interface SearchService {

    /**
     * The search endpoint
     *
     * @param term the text string you want to search for
     * @param countryCode the two-letter country code for the store you want to search
     * @param media the media type you want to search for
     *
     * @return the [Response] with the [SearchResponse] model
     */
    @GET("search")
    suspend fun searchAppleStore(
        @Query("term") term: String,
        @Query("country") countryCode: String,
        @Query("media") media: String,
    ): Response<SearchResponse>
}