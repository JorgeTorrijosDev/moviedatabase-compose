package com.example.moviedatabase.ui.fav

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter

@Composable
fun FavScreen(navController: NavController, viewModel: FavViewModel = hiltViewModel()) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Text(text = "Favorites movies: ", color = Color.White, fontSize = 20.sp)

        val favMovies = viewModel.favMovies

        LazyVerticalGrid(modifier = Modifier.fillMaxWidth(), columns = GridCells.Fixed(3)) {
            items(favMovies.size) {
                Text(text = favMovies[it].name)
                Box(contentAlignment = Alignment.Center) {
                    Image(
                        modifier = Modifier
                            .height(225.dp)
                            .fillMaxWidth(),
                        painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/original" + favMovies[it].poster),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds
                    )
                }
            }
        }
    }


}