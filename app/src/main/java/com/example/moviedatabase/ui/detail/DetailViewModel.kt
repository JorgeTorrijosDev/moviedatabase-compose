package com.example.moviedatabase.ui.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviedatabase.domain.usecases.GetMovieDetail
import com.example.moviedatabase.ui.home.DiscoverListState
import com.example.moviedatabase.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetail,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _movieDetailState = mutableStateOf(MovieDetailState())
    val movieDetailState: State<MovieDetailState> = _movieDetailState

    init {
        savedStateHandle.get<String>("movieId")?.let { movieId ->
            getMovieDetail(movieId)
        }
    }

    private fun getMovieDetail(movieId: String){
        getMovieDetailUseCase.invoke(movieId.toInt()).onEach { result->
            when(result){
                is NetworkResult.Success -> {
                    _movieDetailState.value = MovieDetailState(
                        movies = result.data
                    )
                }

                is NetworkResult.Error -> {
                    _movieDetailState.value = MovieDetailState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }

                is NetworkResult.Loading -> {
                    _movieDetailState.value = MovieDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}