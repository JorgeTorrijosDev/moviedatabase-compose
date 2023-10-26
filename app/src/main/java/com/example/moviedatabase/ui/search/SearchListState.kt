package com.example.moviedatabase.ui.search

import com.example.moviedatabase.domain.Movie

data class SearchListState(
    val query: String? = null,
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val error: String = ""
)
