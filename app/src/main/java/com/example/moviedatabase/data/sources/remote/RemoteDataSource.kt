package com.example.moviedatabase.data.sources.remote

import com.example.moviedatabase.data.model.toMovie
import com.example.moviedatabase.data.model.toMovieDetails
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val movieDataBaseService: MovieDataBaseService) :
    BaseApiResponse() {

    suspend fun getDiscoverMovies() =
        safeApiCall(apiCall = { movieDataBaseService.getDiscoverMovies() }, transform = { it ->
            it.results.map { it.toMovie() }
        })

    suspend fun getTopRatedMovies() =
        safeApiCall(apiCall = { movieDataBaseService.getTopRatedMovies() }, transform = { it ->
            it.results.map { it.toMovie() }
        })

    suspend fun getPopularMovies() =
        safeApiCall(apiCall = { movieDataBaseService.getPopularMovies() }, transform = { it ->
            it.results.map { it.toMovie() }
        })

    suspend fun searchMovie(query: String) =
        safeApiCall(apiCall = { movieDataBaseService.searchMovie(query) }, transform = { it ->
            it.results.map { it.toMovie() }
        })

    suspend fun getMovieDetail(id: Int) =
        safeApiCall(apiCall = { movieDataBaseService.getMovieDetail(id) }, transform = { it ->
            it.toMovieDetails()
        })
}