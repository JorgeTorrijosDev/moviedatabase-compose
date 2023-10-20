package com.example.moviedatabase.domain.usecases

import com.example.moviedatabase.data.repositories.MoviesRepository
import javax.inject.Inject

class GetMovieDetail @Inject constructor(private val moviesRepository: MoviesRepository) {
    operator fun invoke(id: Int) = moviesRepository.getMovieDetails(id)
}