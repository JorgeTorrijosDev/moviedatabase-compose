package com.example.moviedatabase.ui.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviedatabase.domain.usecases.GetDiscoverMovies
import com.example.moviedatabase.domain.usecases.GetPopularMovies
import com.example.moviedatabase.domain.usecases.GetTopRatedMovies
import com.example.moviedatabase.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getDiscoverMovies: GetDiscoverMovies,
    private val getTopRatedMovies: GetTopRatedMovies,
    private val getPopularMovies: GetPopularMovies
) :
    ViewModel() {

    private val _discoverState = mutableStateOf(DiscoverListState())
    val discoverState: State<DiscoverListState> = _discoverState

    init {
        getDiscover()
    }

    private fun getDiscover() {
        getDiscoverMovies().onEach { result ->
            when (result) {
                is NetworkResult.Success -> {
                    _discoverState.value = DiscoverListState(
                        movies = result.data ?: emptyList()
                    )
                }

                is NetworkResult.Error -> {
                    _discoverState.value = DiscoverListState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }

                is NetworkResult.Loading -> {
                    _discoverState.value = DiscoverListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}