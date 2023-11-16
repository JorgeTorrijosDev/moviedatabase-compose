package com.example.moviedatabase.domain.usecases

import com.example.moviedatabase.data.model.toMovieDetail
import com.example.moviedatabase.data.sources.local.MovieDataBaseRepositoryLocal
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFavMovie @Inject constructor(private val movieDataBaseRepositoryLocal: MovieDataBaseRepositoryLocal) {
    fun invoke(id: Int) = movieDataBaseRepositoryLocal.getFavMovie(id).map {it.map { it.toMovieDetail() }}
}