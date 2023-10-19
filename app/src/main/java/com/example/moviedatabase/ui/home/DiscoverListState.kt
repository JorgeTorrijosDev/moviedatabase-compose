package com.example.moviedatabase.ui.home

import com.example.moviedatabase.domain.Movie

data class DiscoverListState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val error: String = ""
)