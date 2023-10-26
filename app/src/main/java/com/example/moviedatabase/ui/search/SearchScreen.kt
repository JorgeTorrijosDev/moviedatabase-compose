package com.example.moviedatabase.ui.search

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.contentColorFor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.moviedatabase.domain.Movie
import com.example.moviedatabase.ui.bottomBar.Screen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavController, viewModel: SearchViewModel = hiltViewModel()) {
    val list by remember {
        viewModel.searchState
    }

    var text by remember { mutableStateOf("") }
    var active by remember {
        mutableStateOf(false)
    }

    Column(modifier =  Modifier.background(Color.Black)) {
        Row {
            SearchBar(
                modifier = Modifier.fillMaxWidth().padding(5.dp),
                query = text,
                onQueryChange = { text = it },
                onSearch = {
                    viewModel.onQueryInput(text)
                    active = false
                },
                active = active,
                onActiveChange = { active = it },
                placeholder = {
                    Text(text = "Search")
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
                }) {

            }
        }
        if (list.movies.isNotEmpty()) {
            Row {
                Text(text = "Results", color = Color.White, fontSize = 25.sp)
            }
        }
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            Box {
                MoviesSearch(
                    topRated = list.movies,
                    navController = navController
                )
            }
        }

    }


}

@Composable
private fun MoviesSearch(topRated: List<Movie>, navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
    ) {
        items(topRated.size) { index ->
            Card(
                shape = RoundedCornerShape(10.dp),
                backgroundColor = MaterialTheme.colors.contentColorFor(Color.Black.copy(alpha = 0.6f)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(5.dp)
                    .background(Color.Black.copy(alpha = 0.6f))
                    .clickable {
                        navController.navigate(Screen.DetailMovieScreen.route + "/${topRated[index].id}")
                    },
                elevation = 5.dp,
                border = BorderStroke(0.5.dp, Color.Blue)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/original" + topRated[index].backDrop),
                        contentDescription = "",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size(175.dp)
                            .padding(5.dp)
                    )
                    Column {
                        Text(
                            text = topRated[index].name,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = topRated[index].releaseDate.toString(),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White,
                        )

                    }

                }
            }
        }
    }
}