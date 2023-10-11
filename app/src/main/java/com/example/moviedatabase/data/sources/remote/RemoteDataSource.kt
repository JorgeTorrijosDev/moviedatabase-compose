package com.example.moviedatabase.data.sources.remote

import com.example.moviedatabase.data.model.toMovie
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val movieDataBaseService: MovieDataBaseService) :
    BaseApiResponse() {

    suspend fun getDiscoverMovies() =
        safeApiCall(apiCall = { movieDataBaseService.getDiscoverMovies() }, transform = { it ->
            it.results.map { it.toMovie() }
        })

}