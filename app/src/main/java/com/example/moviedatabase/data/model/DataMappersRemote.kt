package com.example.moviedatabase.data.model

import com.example.moviedatabase.data.model.movieDetails.MovieDetailsResponse
import com.example.moviedatabase.domain.Movie
import com.example.moviedatabase.domain.MovieDetail

fun MovieResult.toMovie(): Movie = Movie(id,originalTitle, backdropPath, posterPath, voteAverage)
fun MovieDetailsResponse.toMovieDetails() : MovieDetail = MovieDetail(id, originalTitle, backdropPath, posterPath, voteAverage, overview)

