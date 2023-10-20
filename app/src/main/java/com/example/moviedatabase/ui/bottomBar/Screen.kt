package com.example.moviedatabase.ui.bottomBar

sealed class Screen (val route: String){
    object DetailMovieScreen: Screen("detail_movie_screen")
}