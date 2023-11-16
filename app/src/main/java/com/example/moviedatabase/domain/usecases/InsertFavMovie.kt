package com.example.moviedatabase.domain.usecases

import com.example.moviedatabase.data.model.toMovieEntity
import com.example.moviedatabase.data.sources.local.MovieDataBaseRepositoryLocal
import com.example.moviedatabase.domain.MovieDetail
import javax.inject.Inject

class InsertFavMovie @Inject constructor(val movieDataBaseRepositoryLocal: MovieDataBaseRepositoryLocal) {
    suspend fun invoke(movie: MovieDetail) =
        movieDataBaseRepositoryLocal.insertFavMovie(movie.toMovieEntity())
}