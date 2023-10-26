package com.example.moviedatabase.ui.search

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviedatabase.domain.usecases.SearchMovie
import com.example.moviedatabase.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchMovie: SearchMovie,
) :
    ViewModel() {

    var queryState by mutableStateOf(SearchListState())
        private set

    private val _searchState = mutableStateOf(SearchListState())
    val searchState: State<SearchListState> = _searchState

    fun onQueryInput(query: String) {
        queryState = queryState.copy(
            query = query
        )
        getSearchMovie(query = queryState.query.toString())
    }

    fun getSearchMovie(query: String) {
        searchMovie(query).onEach { result ->
            when (result) {
                is NetworkResult.Success -> {
                    _searchState.value = SearchListState(
                        movies = result.data ?: emptyList()
                    )
                }

                is NetworkResult.Error -> {
                    _searchState.value = SearchListState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }

                is NetworkResult.Loading -> {
                    _searchState.value = SearchListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}