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

    val lastNetworkCall: LiveData<Long?> = searchInteractors.getLastNetworkCallUseCase().asLiveData()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    /**
     * Will check the database for search results. If null, will execute [apiSearch]
     */
    fun checkCacheOrSearch() {
        viewModelScope.launch {
            searchInteractors.getAllSearchResultCacheUseCase().collect { cache ->
                if (cache.isEmpty()) {
                    apiSearch()
                } else {
                    _searchResultList.value = cache
                }
            }
        }
    }

    /**
     * Retrieve the search results from iTunes search API
     */
    fun apiSearch() {
        _isLoading.value = true
        viewModelScope.launch {
            searchInteractors.searchAppleStoreUseCase().collect { result ->
                when (result) {
                    is Result.Success -> {
                        val searchResultList = result.value.results
                        _searchResultList.value = searchResultList
                        searchInteractors.insertSearchResultCacheUseCase(searchResultList)
                    }

                    is Result.Error -> {
                        _errorMessage.value = Event(result.errorMessage)
                    }
                }
                _isLoading.value = false
            }
        }
    }

    /**
     * Get the search result by [SearchResult.trackId]
     *
     * @return the [SearchResult], `null` if no match was found
     */
    fun getSearchResult(id: Long): SearchResult? {
        searchResultList.value?.let { list ->
            return list.find { it.trackId == id }
        }
        return null
    }
}