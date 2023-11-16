package com.example.moviedatabase.ui.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviedatabase.domain.usecases.DeleteMovieById
import com.example.moviedatabase.domain.usecases.GetFavMovie
import com.example.moviedatabase.domain.usecases.GetMovieDetail
import com.example.moviedatabase.domain.usecases.InsertFavMovie
import com.example.moviedatabase.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetail,
    private val insertFavMovie: InsertFavMovie,
    private val getFavMovie: GetFavMovie,
    private val deleteMovieById: DeleteMovieById,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var isFav by mutableStateOf(false)
        private set
    var id by mutableStateOf(0)
        private set

    private val _movieDetailState = mutableStateOf(MovieDetailState())
    val movieDetailState: State<MovieDetailState> = _movieDetailState

    init {
        savedStateHandle.get<String>("movieId")?.let { movieId ->
            id = movieId.toInt()
            getMovieDetail(movieId)
            checkIsFav()
        }
    }

    fun onChangeFav() {
        isFav = !isFav
        if (isFav) {
            movieDetailState.value.movies?.let {
                viewModelScope.launch {
                    insertFavMovie.invoke(it)
                }
            }
        } else {
            movieDetailState.value.movies?.let {
                viewModelScope.launch {
                    deleteMovieById.invoke(id)
                }
            }
        }

    }

    private fun checkIsFav() {
        viewModelScope.launch {
            getFavMovie.invoke(id).collect {
                if (it.isNotEmpty()) {
                    isFav = true
                }
            }
        }
    }

    private fun getMovieDetail(movieId: String) {
        getMovieDetailUseCase.invoke(movieId.toInt()).onEach { result ->
            when (result) {
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