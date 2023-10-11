package com.example.moviedatabase.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviedatabase.domain.usecases.GetDiscoverMovies
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
class HomeViewModel @Inject constructor(private val getDiscoverMovies: GetDiscoverMovies) :
    ViewModel() {

    init {
        handleEvent(HomeContract.Event.GetData)
    }

    private val _uiStateMovies: MutableStateFlow<HomeContract.State> by lazy {
        MutableStateFlow(HomeContract.State())
    }
    val uiStatemovies: StateFlow<HomeContract.State> = _uiStateMovies


    private val _uiError = Channel<String>()
    val uiError = _uiError.receiveAsFlow()

    fun handleEvent(event: HomeContract.Event) {
        when (event) {
            is HomeContract.Event.GetData -> {
                viewModelScope.launch {
                    getDiscoverMovies.invoke()
                        .catch(action = { cause -> _uiError.send(cause.message ?: "") })
                        .collect { result ->
                            when (result) {
                                is NetworkResult.Error -> {
                                    _uiStateMovies.update { it.copy(error = result.message) }
                                }

                                is NetworkResult.Loading -> _uiStateMovies.update {
                                    it.copy(
                                        isLoading = true
                                    )
                                }

                                is NetworkResult.Success -> _uiStateMovies.update {
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