package com.example.moviedatabase.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val lista = viewModel.uiStatemovies.collectAsState()
    val pagerState = rememberPagerState()
    if (lista.value.movies.isNotEmpty()) {
        Column {
            Row {
                HorizontalPager(
                    pageCount = lista.value.movies.size,
                    state = pagerState,
                    key = { lista.value.movies[it].name }) { index ->
                    Box(contentAlignment = Alignment.BottomStart) {
                        Image(
                            modifier = Modifier
                                .height(225.dp)
                                .fillMaxWidth(),
                            painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/original" + lista.value.movies[index].img),
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds
                        )
                        Box {
                            Text(
                                text = lista.value.movies[index].name,
                                color = Color.White,
                                fontSize = 20.sp,
                                modifier = Modifier
                                    .padding(6.dp)
                                    .border(1.dp, Color.Blue.copy(alpha = 0.6f), CircleShape)
                                    .background(Color.Black.copy(alpha = 0.6f), CircleShape)
                                    .padding(16.dp)
                            )
                        }
                    }
                }
            }
            Row {
                Text(text = "Top rated", color = Color.White, fontSize = 20.sp)
            }
        }
    }
}