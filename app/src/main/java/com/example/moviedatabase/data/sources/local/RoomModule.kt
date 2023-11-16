package com.example.moviedatabase.data.sources.local

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun provideDataBase(
        @ApplicationContext context: Context,
    ) = Room.databaseBuilder(
        context,
        MovieDataBase::class.java,
        "movie_data_base"
    )
        .fallbackToDestructiveMigrationFrom(2)
        .build()

    @Provides
    fun providesMoviesDao(moviesDataBase: MovieDataBase) =
        moviesDataBase.moviesDataBaseDAO()
}