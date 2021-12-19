package com.pnevalle.itunessearch.domain

import com.pnevalle.itunessearch.domain.database.GetAllSearchResultCacheUseCase
import com.pnevalle.itunessearch.domain.database.InsertSearchResultCacheUseCase
import javax.inject.Inject

/**
 * The search interactor class containing the search use cases. This
 * class will be responsible for interacting with the UI and data.
 *
 * Contains the use cases for network and database.
 *
 * @param searchAppleStoreUseCase the search use case
 */
class SearchInteractors @Inject constructor(
    val searchAppleStoreUseCase: SearchAppleStoreUseCase,
    val getLastNetworkCallUseCase: GetLastNetworkCallUseCase,
    val getAllSearchResultCacheUseCase: GetAllSearchResultCacheUseCase,
    val insertSearchResultCacheUseCase: InsertSearchResultCacheUseCase
)