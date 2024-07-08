package com.phisit.composenetfilm.search

import android.util.Log
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.phisit.composenetfilm.MovieDetailRouteScreen
import com.phisit.composenetfilm.domain.MovieModel
import com.phisit.composenetfilm.widget.ConstraintHorizontalPoster
import com.phisit.composenetfilm.widget.Loading
import com.phisit.composenetfilm.widget.SearchBar
import com.phisit.composenetfilm.`ีutils`.debounceClickable
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = koinViewModel()
) {

    val uiState = viewModel.uiState.collectAsState()

    DisposableEffect(key1 = uiState.value) {
        Log.e("SearchScreen", "entry compose $uiState")
        onDispose {
            Log.e("SearchScreen", "leaves compose")
        }
    }

    SearchScreenStateful(
        modifier = modifier,
        isLoading = uiState.value.isLoading,
        emptyDate = uiState.value.isEmptyResult,
        movieList = uiState.value.movieList,
        onSearch = { viewModel.search(it) },
        onClickedFavorite = { movie, favorite ->
            viewModel.updateFavorite(movie, favorite)
        },
    ) { movie ->
        navController.navigate(MovieDetailRouteScreen(movie))
    }
}

@Composable
fun SearchScreenStateful(
    isLoading: Boolean,
    emptyDate: Boolean,
    modifier: Modifier = Modifier,
    movieList: List<MovieModel> = listOf(),
    onSearch: (String) -> Unit,
    onClickedFavorite: (MovieModel, Boolean) -> Unit,
    onClicked: (MovieModel) -> Unit
) {
    var searchText by remember {
        mutableStateOf("")
    }
    var clearFocus by remember {
        mutableStateOf(false)
    }

    val listState = rememberLazyListState()

    val isHasScrolled by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 0
        }
    }

    LaunchedEffect(isHasScrolled) {
        if (isHasScrolled) clearFocus = true
    }

    Column(
        modifier = modifier.pointerInput(Unit) {
            detectTapGestures(onTap = {
                clearFocus = true
            })
        },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchBar(
            value = searchText,
            placeholder = "Search your movies",
            clearFocus = clearFocus,
            onValueChanged = {
                searchText = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 16.dp),
            keyboardAction = KeyboardActions(
                onSearch = { onSearch(searchText) }
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Loading(
                    Modifier
                        .size(75.dp)
                )
            }
        } else if (emptyDate) {
            SearchNotFound()
        } else {
            SearchResult(
                stateList = listState,
                movieList = movieList,
                modifier = Modifier,
                onClicked = onClicked,
                onClickedFavorite = onClickedFavorite
            )
        }
    }
}

@Composable
fun SearchResult(
    stateList: LazyListState,
    modifier: Modifier = Modifier,
    movieList: List<MovieModel> = listOf(),
    onClicked: (MovieModel) -> Unit,
    onClickedFavorite: (MovieModel, Boolean) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        state = stateList
    ) {
        items(movieList, key = {
            it.id
        }) { item ->
            ConstraintHorizontalPoster(
                movie = item,
                modifier = Modifier
                    .fillMaxWidth()
                    .debounceClickable {
                        onClicked(item)
                    },
                onClickedFavorite = onClickedFavorite
            )
        }
    }
}

@Composable
fun SearchNotFound(
    modifier: Modifier = Modifier
) {
    Box(
        modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Movie not found")
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchScreenStatefulPreview() {
    val movieList = listOf(
        MovieModel(
            "1",
            "Hello world",
            "",
            "https://miro.medium.com/v2/resize:fit:828/format:webp/1*3_Qt-ypfyOms6KMYtKmGxQ.png",
            initialFavorite = true, rating = "8/10", comment = "200k"
        ),
        MovieModel(
            "2",
            "Lorem Ipsum is simply dummy text",
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
            "https://github.com/Rubikkube/compose-movie/blob/main/images/home_light_.png",
            initialFavorite = true, rating = "8/10", comment = "200k"
        )
    )

    SearchScreenStateful(
        modifier = Modifier
            .fillMaxSize(),
        isLoading = false,
        emptyDate = false,
        movieList = movieList,
        onSearch = {},
        onClickedFavorite = {_, _ ->},
        onClicked = {}
    )
}