package com.example.moviedatabase.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.CircularProgressIndicator
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
import com.example.moviedatabase.domain.Movie
import com.example.moviedatabase.ui.bottomBar.Screen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {
    val discover = viewModel.discoverState
    val topRated = viewModel.topRatedState
    val popular = viewModel.popularState


    val pagerState = rememberPagerState()


    if (discover.value.isLoading || topRated.value.isLoading || popular.value.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CustomCircularProgressBar()
        }
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .background(Color.Black)
                    .fillMaxSize()
            ) {
                Row {
                    HorizontalPaggerDiscover(
                        pagerState = pagerState,
                        discover = discover.value.movies,
                        navController = navController
                    )
                }
                Row {
                    Text(text = "Top rated movies", color = Color.White, fontSize = 20.sp)
                }
                Row {
                    Box {
                        TopRatedMoviesLazyRow(
                            topRated = topRated.value.movies,
                            navController = navController
                        )
                    }
                }
                Row {
                    Text(text = "Popular movies", color = Color.White, fontSize = 20.sp)
                }
                Row {
                    Box {
                        PopularMoviesLazyRow(
                            popular = popular.value.movies,
                            navController = navController
                        )
                    }

                }
            }
        }

    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun HorizontalPaggerDiscover(
    pagerState: PagerState,
    discover: List<Movie>,
    navController: NavController
) {
    HorizontalPager(
        pageCount = discover.size,
        state = pagerState,
        key = { discover[it].name }) { index ->
        Box(
            contentAlignment = Alignment.BottomStart,
            modifier = Modifier.clickable {
                navController.navigate(Screen.DetailMovieScreen.route + "/${discover[index].id}")
            }) {
            Image(
                modifier = Modifier
                    .height(225.dp)
                    .fillMaxWidth(),
                painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/original" + discover[index].backDrop),
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
            Box {
                Text(
                    text = discover[index].name,
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

@Composable
private fun PopularMoviesLazyRow(popular: List<Movie>, navController: NavController) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(125.dp)
    ) {
        items(popular.size) { index ->
            Card(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .width(185.dp)
                    .padding(5.dp)
                    .clickable {
                        navController.navigate(Screen.DetailMovieScreen.route + "/${popular[index].id}")
                    },
                elevation = 5.dp,
                border = BorderStroke(0.5.dp, Color.Blue)
            ) {
                Box(modifier = Modifier.width(100.dp)) {
                    Image(
                        contentScale = ContentScale.Crop,
                        painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/original" + popular[index].backDrop),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
private fun TopRatedMoviesLazyRow(topRated: List<Movie>, navController: NavController) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(175.dp)
    ) {
        items(topRated.size) { index ->
            Card(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(5.dp)
                    .clickable {
                        navController.navigate(Screen.DetailMovieScreen.route + "/${topRated[index].id}")
                    },
                elevation = 5.dp,
                border = BorderStroke(0.5.dp, Color.Blue)
            ) {
                Box(modifier = Modifier.width(100.dp)) {
                    Image(
                        contentScale = ContentScale.Crop,
                        painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/original" + topRated[index].poster),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )
                    Text(
                        text = topRated[index].voteAverage.toString(),
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

@Composable
fun CustomCircularProgressBar() {
    CircularProgressIndicator(
        modifier = Modifier.size(100.dp),
        color = Color.Blue,
        strokeWidth = 10.dp
    )
}

