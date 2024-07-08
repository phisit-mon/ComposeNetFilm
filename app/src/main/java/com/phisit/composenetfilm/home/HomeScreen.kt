package com.phisit.composenetfilm.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.phisit.composenetfilm.MovieDetailRouteScreen
import com.phisit.composenetfilm.SearchRouteScreen
import com.phisit.composenetfilm.domain.MovieModel
import com.phisit.composenetfilm.widget.MovieHorizontalShelf
import com.phisit.composenetfilm.widget.MovieVerticalShelf
import com.phisit.composenetfilm.widget.SearchBar
import com.phisit.composenetfilm.`ีutils`.debounceClickable
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel()
) {

    val movieList: List<MovieModel> = viewModel.movieList
    val movieReverseList: List<MovieModel> = viewModel.movieReverseList

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        SearchBar(
            value = "",
            placeholder = "Search your movies",
            enable = false,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 16.dp)
                .debounceClickable {
                    navController.navigate(SearchRouteScreen)
                }
        )
        MovieVerticalShelf(
            title = "Coming soon",
            movies = movieList,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 12.dp),
            onClickItem = { item ->
                navController.navigate(
                    MovieDetailRouteScreen(
                        movieModel = item.copy()
                    )
                )
            }, onClickedFavorite = { movie, favorite ->
                viewModel.updateFavoriteMovie(movie, favorite)
            },
            onClickComment = { item ->

            }
        )

        MovieHorizontalShelf(
            title = "Watching now",
            movies = movieReverseList,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = 12.dp),
            onClickItem = { item ->
                navController.navigate(MovieDetailRouteScreen(movieModel = item))
            }, onClickedFavorite = { movie, favorite ->
                viewModel.updateReverseFavoriteMovie(movie, favorite)
            },
            onClickComment = { item ->

            }
        )
    }
}