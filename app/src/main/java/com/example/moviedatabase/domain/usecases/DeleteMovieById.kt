package com.example.moviedatabase.domain.usecases

import com.example.moviedatabase.data.sources.local.MovieDataBaseRepositoryLocal
import javax.inject.Inject

class DeleteMovieById @Inject constructor(private val movieDataBaseRepositoryLocal: MovieDataBaseRepositoryLocal) {
    suspend fun invoke(id: Int) = movieDataBaseRepositoryLocal.deleteMovieById(id)
}