package com.example.moviedatabase.data.sources.local

import com.example.moviedatabase.data.model.local.MovieEntity
import javax.inject.Inject

class MovieDataBaseRepositoryLocal @Inject constructor(private val moviesDataBaseDAO: MoviesDataBaseDAO) {
    suspend fun insertFavMovie(movieEntity: MovieEntity) =
        moviesDataBaseDAO.insertFavMovie(movieEntity)
    fun getFavMovie(id: Int) = moviesDataBaseDAO.getMovieById(id)

    suspend fun deleteMovieById(id: Int) = moviesDataBaseDAO.deleteMovieById(id)
}