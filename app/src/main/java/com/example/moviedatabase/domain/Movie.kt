package com.example.moviedatabase.domain

data class Movie(
    val id: Int,
    val name: String,
    val backDrop: String?,
    val poster: String?,
    val voteAverage: Double,
    val releaseDate: String,
)