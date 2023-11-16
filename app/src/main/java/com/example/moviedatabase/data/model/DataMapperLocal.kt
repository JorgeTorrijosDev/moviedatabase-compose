package com.example.moviedatabase.data.model

import com.example.moviedatabase.data.model.local.MovieEntity
import com.example.moviedatabase.domain.MovieDetail

fun MovieDetail.toMovieEntity(): MovieEntity =
    MovieEntity(id, name)

fun MovieEntity.toMovieDetail(): MovieDetail =
    MovieDetail(id, name,null,null,null,null)