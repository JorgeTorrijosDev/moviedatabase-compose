package com.example.moviedatabase.domain.usecases

import com.example.moviedatabase.data.model.toMovie
import com.example.moviedatabase.data.sources.local.MovieDataBaseRepositoryLocal
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllFavMovies @Inject constructor(private val movieDataBaseRepositoryLocal: MovieDataBaseRepositoryLocal) {
    fun invoke() = movieDataBaseRepositoryLocal.getAllFavMovies().map { it.map { it.toMovie() } }
}