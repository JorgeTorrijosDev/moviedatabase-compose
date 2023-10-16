package com.example.moviedatabase.domain.usecases

import com.example.moviedatabase.data.repositories.MoviesRepository
import javax.inject.Inject

class GetPopularMovies @Inject constructor(private val moviesRepository: MoviesRepository) {
    fun invoke() = moviesRepository.getPopularMovies()
}