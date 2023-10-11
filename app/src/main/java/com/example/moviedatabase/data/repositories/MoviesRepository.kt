package com.example.moviedatabase.data.repositories

import com.example.moviedatabase.utils.NetworkResult
import com.example.moviedatabase.data.sources.remote.RemoteDataSource
import com.example.moviedatabase.domain.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) {
    fun getDiscoverMovies(): Flow<NetworkResult<List<Movie>>> {
        return flow {
            emit(NetworkResult.Loading())
            val result = remoteDataSource.getDiscoverMovies()
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
}