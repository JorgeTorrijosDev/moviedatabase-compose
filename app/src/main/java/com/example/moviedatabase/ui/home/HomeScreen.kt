package com.example.moviedatabase.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
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
    val topRatedlista = viewModel.uiStateTopRatedMovies.collectAsState()
    val topRatedListItems = remember { topRatedlista }

    val popularMovies = viewModel.uiStatePopularMovies.collectAsState()
    val popularMoviesItems = remember {
        popularMovies
    }
    val pagerState = rememberPagerState()
    Box(
        modifier = Modifier
            .background(Color.Black)
    ) {
        if (lista.value.movies.isNotEmpty()) {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
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
                                painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/original" + lista.value.movies[index].backDrop),
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
                    Text(text = "Top rated movies", color = Color.White, fontSize = 20.sp)
                }
                Row {
                    if (topRatedlista.value.movies.isNotEmpty()) {
                        Box {
                            LazyRow(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(175.dp)
                            ) {
                                items(topRatedListItems.value.movies.size) { index ->
                                    Card(
                                        shape = RoundedCornerShape(10.dp),
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .padding(5.dp),
                                        elevation = 5.dp,
                                        border = BorderStroke(0.5.dp, Color.Blue)
                                    ) {
                                        Box(modifier = Modifier.width(100.dp)) {
                                            Image(
                                                contentScale = ContentScale.Crop,
                                                painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/original" + topRatedListItems.value.movies[index].poster),
                                                contentDescription = null,
                                                modifier = Modifier.fillMaxSize()
                                            )
                                            Text(
                                                text = topRatedListItems.value.movies[index].voteAverage.toString(),
                                                color = Color.White,
                                                modifier = Modifier
                                                    .padding(6.dp)
                                                    .border(
                                                        1.dp,
                                                        Color.Blue.copy(alpha = 0.6f),
                                                        CircleShape
                                                    )
                                                    .background(
                                                        Color.Black.copy(alpha = 0.3f),
                                                        CircleShape
                                                    )
                                                    .padding(6.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                Row {
                    Text(text = "Popular movies", color = Color.White, fontSize = 20.sp)
                }
                Row {
                    if (popularMovies.value.movies.isNotEmpty()) {
                        Box {
                            LazyRow(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(125.dp)
                            ) {
                                items(popularMoviesItems.value.movies.size) { index ->
                                    Card(
                                        shape = RoundedCornerShape(10.dp),
                                        modifier = Modifier
                                            .width(185.dp)
                                            .padding(5.dp),
                                        elevation = 5.dp,
                                        border = BorderStroke(0.5.dp, Color.Blue)
                                    ) {
                                        Box(modifier = Modifier.width(100.dp)) {
                                            Image(
                                                contentScale = ContentScale.Crop,
                                                painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/original" + popularMoviesItems.value.movies[index].backDrop),
                                                contentDescription = null,
                                                modifier = Modifier.fillMaxSize()
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

                }
                Row {
                    Text(text = "Popular movies", color = Color.White, fontSize = 20.sp)
                }
                Row {
                    if (popularMovies.value.movies.isNotEmpty()) {
                        Box {
                            LazyRow(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(125.dp)
                            ) {
                                items(popularMoviesItems.value.movies.size) { index ->
                                    Card(
                                        shape = RoundedCornerShape(10.dp),
                                        modifier = Modifier
                                            .width(185.dp)
                                            .padding(5.dp),
                                        elevation = 5.dp,
                                        border = BorderStroke(0.5.dp, Color.Blue)
                                    ) {
                                        Box(modifier = Modifier.width(100.dp)) {
                                            Image(
                                                contentScale = ContentScale.Crop,
                                                painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/original" + popularMoviesItems.value.movies[index].backDrop),
                                                contentDescription = null,
                                                modifier = Modifier.fillMaxSize()
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

                }
                Row {
                    Text(text = "Top rated movies", color = Color.White, fontSize = 20.sp)
                }
                Row {
                    if (topRatedlista.value.movies.isNotEmpty()) {
                        Box {
                            LazyRow(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(175.dp)
                            ) {
                                items(topRatedListItems.value.movies.size) { index ->
                                    Card(
                                        shape = RoundedCornerShape(10.dp),
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .padding(5.dp),
                                        elevation = 5.dp,
                                        border = BorderStroke(0.5.dp, Color.Blue)
                                    ) {
                                        Box(modifier = Modifier.width(100.dp)) {
                                            Image(
                                                contentScale = ContentScale.Crop,
                                                painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/original" + topRatedListItems.value.movies[index].poster),
                                                contentDescription = null,
                                                modifier = Modifier.fillMaxSize()
                                            )
                                            Text(
                                                text = topRatedListItems.value.movies[index].voteAverage.toString(),
                                                color = Color.White,
                                                modifier = Modifier
                                                    .padding(6.dp)
                                                    .border(
                                                        1.dp,
                                                        Color.Blue.copy(alpha = 0.6f),
                                                        CircleShape
                                                    )
                                                    .background(
                                                        Color.Black.copy(alpha = 0.3f),
                                                        CircleShape
                                                    )
                                                    .padding(6.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
