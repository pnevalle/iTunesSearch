package com.pnevalle.itunessearch.ui

import androidx.lifecycle.*
import com.pnevalle.itunessearch.common.Event
import com.pnevalle.itunessearch.data.Result
import com.pnevalle.itunessearch.data.SearchResult
import com.pnevalle.itunessearch.domain.SearchInteractors
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * The view model class for [SearchListFragment]
 */
@HiltViewModel
class SearchViewModel @Inject constructor(private val searchInteractors: SearchInteractors) :
    ViewModel() {

    private val _searchResultList = MutableLiveData<List<SearchResult>>()
    val searchResultList: LiveData<List<SearchResult>> = _searchResultList

    private val _errorMessage = MutableLiveData<Event<String>>()
    val errorMessage: LiveData<Event<String>> = _errorMessage

    val lastNetworkCall: LiveData<Long> = searchInteractors.getLastNetworkCallUseCase().asLiveData()

    /**
     * Retrieve the search results from iTunes search API
     */
    fun search() {
        viewModelScope.launch {
            searchInteractors.searchAppleStoreUseCase().collect { result ->
                when (result) {
                    is Result.Success -> {
                        _searchResultList.value = result.value.results
                    }

                    is Result.Error -> {
                        _errorMessage.value = Event(result.errorMessage)
                    }
                }
            }
        }
    }
}