package com.example.moviedatabase.ui.fav

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviedatabase.domain.Movie
import com.example.moviedatabase.domain.usecases.GetAllFavMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavViewModel @Inject constructor(
    private val getAllFavMovies: GetAllFavMovies
) : ViewModel() {

    var favMovies = emptyList<Movie>()
        private set

    init {
        getAllFavMovies()
    }

    private fun getAllFavMovies() {
        viewModelScope.launch {
            getAllFavMovies.invoke().collect {
                if (it.isNotEmpty()) {
                    favMovies = it
                }
            }
        }
    }
}