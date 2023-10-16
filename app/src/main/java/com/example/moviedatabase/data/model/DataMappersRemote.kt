package com.example.moviedatabase.data.model

import com.example.moviedatabase.domain.Movie

fun MovieResult.toMovie(): Movie = Movie(originalTitle, backdropPath, posterPath, voteAverage)

