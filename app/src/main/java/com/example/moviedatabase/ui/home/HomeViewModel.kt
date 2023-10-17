package com.example.moviedatabase.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviedatabase.domain.usecases.GetDiscoverMovies
import com.example.moviedatabase.domain.usecases.GetPopularMovies
import com.example.moviedatabase.domain.usecases.GetTopRatedMovies
import com.example.moviedatabase.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getDiscoverMovies: GetDiscoverMovies,
    private val getTopRatedMovies: GetTopRatedMovies,
    private val getPopularMovies: GetPopularMovies
) :
    ViewModel() {

    init {
        handleEvent(HomeContract.Event.GetDiscoverMovies)
        handleEvent(HomeContract.Event.GetTopRatedMovies)
        handleEvent(HomeContract.Event.GetPopularMovies)
    }

    private val _uiDiscoverStateMovies: MutableStateFlow<HomeContract.DiscoverState> by lazy {
        MutableStateFlow(HomeContract.DiscoverState())
    }
    val uiStatemovies: StateFlow<HomeContract.DiscoverState> = _uiDiscoverStateMovies

    private val _uiTopRatedStateTopRatedMovies: MutableStateFlow<HomeContract.TopRatedState> by lazy {
        MutableStateFlow(HomeContract.TopRatedState())
    }
    val uiTopRatedStateTopRatedMovies: StateFlow<HomeContract.TopRatedState> = _uiTopRatedStateTopRatedMovies

    private val _uiPopularStatePopularMovies: MutableStateFlow<HomeContract.PopularSate> by lazy {
        MutableStateFlow(HomeContract.PopularSate())
    }
    val uiPopularStatePopularMovies: StateFlow<HomeContract.PopularSate> = _uiPopularStatePopularMovies

    private val _uiError = Channel<String>()
    val uiError = _uiError.receiveAsFlow()

    fun handleEvent(event: HomeContract.Event) {
        when (event) {
            is HomeContract.Event.GetDiscoverMovies -> {
                viewModelScope.launch {
                    getDiscoverMovies.invoke()
                        .catch(action = { cause -> _uiError.send(cause.message ?: "") })
                        .collect { result ->
                            when (result) {
                                is NetworkResult.Error -> {
                                    _uiDiscoverStateMovies.update { it.copy(error = result.message) }
                                }

                                is NetworkResult.Loading -> _uiDiscoverStateMovies.update {
                                    it.copy(
                                        isLoading = true
                                    )
                                }

                                is NetworkResult.Success -> _uiDiscoverStateMovies.update {
                                    it.copy(
                                        movies = result.data ?: emptyList(), isLoading = false
                                    )
                                }
                            }
                        }
                }
            }

            is HomeContract.Event.GetTopRatedMovies -> {
                viewModelScope.launch {
                    getTopRatedMovies.invoke()
                        .catch(action = { cause -> _uiError.send(cause.message ?: "") })
                        .collect { result ->
                            when (result) {
                                is NetworkResult.Error -> {
                                    _uiTopRatedStateTopRatedMovies.update { it.copy(error = result.message) }
                                }

                                is NetworkResult.Loading -> _uiTopRatedStateTopRatedMovies.update {
                                    it.copy(
                                        isLoading = true
                                    )
                                }

                                is NetworkResult.Success -> _uiTopRatedStateTopRatedMovies.update {
                                    it.copy(
                                        movies = result.data ?: emptyList(), isLoading = false
                                    )
                                }
                            }
                        }
                }
            }

            is HomeContract.Event.GetPopularMovies -> {
                viewModelScope.launch {
                    getPopularMovies.invoke()
                        .catch(action = { cause -> _uiError.send(cause.message ?: "") })
                        .collect { result ->
                            when (result) {
                                is NetworkResult.Error -> {
                                    _uiPopularStatePopularMovies.update { it.copy(error = result.message) }
                                }

                                is NetworkResult.Loading -> _uiPopularStatePopularMovies.update {
                                    it.copy(
                                        isLoading = true
                                    )
                                }

                                is NetworkResult.Success -> _uiPopularStatePopularMovies.update {
                                    it.copy(
                                        movies = result.data ?: emptyList(), isLoading = false
                                    )
                                }
                            }
                        }
                }
            }
        }
    }
}