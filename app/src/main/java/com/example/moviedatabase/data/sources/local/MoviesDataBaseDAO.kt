package com.example.moviedatabase.data.sources.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviedatabase.data.model.local.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDataBaseDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavMovie(movieEntity: MovieEntity)

    @Query("select id from moviesfav where id = :id")
    fun getMovieById(id: Int): Flow<List<MovieEntity>>

    @Query("DELETE FROM moviesfav WHERE id = :id")
    suspend fun deleteMovieById(id: Int)

    @Query("select * from moviesfav")
    fun getAllFavMovies(): Flow<List<MovieEntity>>

}