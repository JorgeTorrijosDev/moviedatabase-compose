package com.example.moviedatabase.ui.home

import com.example.moviedatabase.domain.Movie

interface HomeContract {

    sealed class Event {
        object GetData : Event()
    }

    data class State(
        val movies: List<Movie> = emptyList(),
        val isLoading: Boolean = false,
        val error: String? = null,
    )

}