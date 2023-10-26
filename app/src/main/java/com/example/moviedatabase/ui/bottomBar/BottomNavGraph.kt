package com.example.moviedatabase.ui.bottomBar

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.moviedatabase.ui.detail.DetailScreen
import com.example.moviedatabase.ui.home.HomeScreen
import com.example.moviedatabase.ui.search.SearchScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomBarScreen.Home.route) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen(navController)
        }
        composable(route = BottomBarScreen.Search.route) {
            SearchScreen(navController)
        }
        composable(route = Screen.DetailMovieScreen.route + "/{movieId}"){
            DetailScreen()
        }
    }
}