package com.pnevalle.itunessearch.domain

import javax.inject.Inject

/**
 * The search interactor class containing the search use cases. This
 * class will be responsible for interacting with the UI and data.
 *
 * @param searchAppleStoreUseCase the search use case
 */
class SearchInteractors @Inject constructor(
    val searchAppleStoreUseCase: SearchAppleStoreUseCase,
    val getLastNetworkCallUseCase: GetLastNetworkCallUseCase
)