package com.example.moviedatabase.data.repositories

import com.example.moviedatabase.data.sources.remote.RemoteDataSource
import com.example.moviedatabase.domain.Movie
import com.example.moviedatabase.domain.MovieDetail
import com.example.moviedatabase.utils.NetworkResult
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

    fun getTopRatedMovies(): Flow<NetworkResult<List<Movie>>> {
        return flow {
            emit(NetworkResult.Loading())
            val result = remoteDataSource.getTopRatedMovies()
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    fun getPopularMovies(): Flow<NetworkResult<List<Movie>>> {
        return flow {
            emit(NetworkResult.Loading())
            val result = remoteDataSource.getPopularMovies()
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
    fun getMovieDetails(id: Int): Flow<NetworkResult<MovieDetail>>{
        return flow {
            emit(NetworkResult.Loading())
            val result = remoteDataSource.getMovieDetail(id)
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
}