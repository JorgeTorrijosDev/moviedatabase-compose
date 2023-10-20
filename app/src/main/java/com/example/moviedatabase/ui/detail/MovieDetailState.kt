package com.example.moviedatabase.ui.detail

import com.example.moviedatabase.domain.MovieDetail

data class MovieDetailState(
    val isLoading: Boolean = false,
    val movies: MovieDetail? = null,
    val error: String = "",
)
