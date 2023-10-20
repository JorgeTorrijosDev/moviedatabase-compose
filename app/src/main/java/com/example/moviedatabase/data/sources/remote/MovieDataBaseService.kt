package com.example.moviedatabase.data.sources.remote

import com.example.moviedatabase.data.model.MoviesResponse
import com.example.moviedatabase.data.model.movieDetails.MovieDetailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDataBaseService {

    @GET("discover/movie")
    suspend fun getDiscoverMovies(): Response<MoviesResponse>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(): Response<MoviesResponse>

    @GET("movie/popular")
    suspend fun getPopularMovies(): Response<MoviesResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") id: Int): Response<MovieDetailsResponse>

}