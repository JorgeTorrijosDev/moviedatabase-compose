package com.example.moviedatabase.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.moviedatabase.ui.home.CustomCircularProgressBar

@Composable
fun DetailScreen(
    detailViewModel: DetailViewModel = hiltViewModel()
) {

    val movieDetailState = detailViewModel.movieDetailState

    if (movieDetailState.value.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CustomCircularProgressBar()
        }
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(225.dp)
                ) {
                    Image(
                        contentScale = ContentScale.Crop,
                        painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/original" + movieDetailState.value.movies?.backDrop),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )
                    FavoriteButton()
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(20.dp)
                            .background(Color.Black.copy(0.5f))
                            .blur(100.dp)
                            .align(Alignment.BottomEnd)
                    )
                }
                Row {
                    Text(
                        text = movieDetailState.value.movies?.name.toString(),
                        color = Color.White,
                        fontSize = 30.sp
                    )
                }
                Row {
                    Text(
                        text = movieDetailState.value.movies?.overview.toString(),
                        color = Color.White
                    )
                }
            }

        }
    }
}

@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier,
    color: Color = Color(0xffE91E63),
    detailViewModel: DetailViewModel = hiltViewModel()
) {

    val isFav = detailViewModel.isFav

    IconToggleButton(
        checked = isFav,
        onCheckedChange = {
            detailViewModel.onChangeFav()
        }
    ) {
        Icon(
            tint = color,
            modifier = modifier.graphicsLayer {
                scaleX = 1.3f
                scaleY = 1.3f
            },
            imageVector = if (detailViewModel.isFav) {
                Icons.Filled.Favorite
            } else {
                Icons.Default.FavoriteBorder
            },
            contentDescription = null
        )
    }

}