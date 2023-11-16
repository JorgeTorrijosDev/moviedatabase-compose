package com.example.moviedatabase.data.sources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviedatabase.data.model.local.MovieEntity

@Database(
    entities = [MovieEntity::class],
    version = 3,
    exportSchema = true
)
abstract class MovieDataBase : RoomDatabase(){
    abstract fun moviesDataBaseDAO(): MoviesDataBaseDAO
}