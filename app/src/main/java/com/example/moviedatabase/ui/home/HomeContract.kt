package com.example.moviedatabase.ui.home

import com.example.moviedatabase.domain.Movie

interface HomeContract {

    sealed class Event {
        object GetDiscoverMovies : Event()
        object GetTopRatedMovies : Event()
        object GetPopularMovies : Event()
    }

    data class DiscoverState(
        val movies: List<Movie> = emptyList(),
        val isLoading: Boolean = false,
        val error: String? = null,
    )

    data class TopRatedState(
        val movies: List<Movie> = emptyList(),
        val isLoading: Boolean = false,
        val error: String? = null,
    )

    data class PopularSate(
        val movies: List<Movie> = emptyList(),
        val isLoading: Boolean = false,
        val error: String? = null,
    )

}