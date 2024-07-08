package com.phisit.composenetfilm.moviedetail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.toRoute
import com.phisit.composenetfilm.MovieDetailRouteScreen
import com.phisit.composenetfilm.domain.MovieModel
import com.phisit.composenetfilm.utils.CoilImage
import com.phisit.composenetfilm.utils.ExpandedText
import com.phisit.composenetfilm.widget.ConstraintHorizontalPoster
import com.phisit.composenetfilm.`ีutils`.debounceClickable
import org.koin.androidx.compose.koinViewModel

val EXPANDED_TOP_BAR_HEIGHT = 256.dp
val COLLAPSED_TOP_BAR_HEIGHT = 56.dp

@Composable
fun MovieDetailScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: MovieDetailViewModel = koinViewModel()
) {

    val movie = remember {
        runCatching {
            navController.getBackStackEntry<MovieDetailRouteScreen>()
                .toRoute<MovieDetailRouteScreen>().movieModel
        }.getOrNull()
    }

    val movieList = viewModel.movieList.reversed().subList(0, 5)

    val overlapHeightPx = with(LocalDensity.current) {
        EXPANDED_TOP_BAR_HEIGHT.toPx() - COLLAPSED_TOP_BAR_HEIGHT.toPx()
    }

    val listState = rememberLazyListState()

    val isCollapsed: Boolean by remember {
        derivedStateOf {
            val isFirstItemHidden = listState.firstVisibleItemScrollOffset > overlapHeightPx
            isFirstItemHidden || listState.firstVisibleItemIndex > 0
        }
    }

    CollapseTopBarContainer(
        extendedTopBar = {
            ExpandedTopBar(title = movie!!.name, posterUrl = movie.posterUrl.orEmpty())
        },
        collapsedTopBar = {
            CollapsedTopBar(
                title = movie!!.name,
                isCollapsed = isCollapsed,
                modifier = Modifier.zIndex(2f)
            )
        },
        listState = listState,
        lazyColumnItem = {
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                MovieDetail(
                    movie = movie!!,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                )
            }
            item {
                Spacer(modifier = Modifier.height(32.dp))
            }
            items(movieList, key = {
                it.id
            }) { item ->
                ConstraintHorizontalPoster(
                    movie = item,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                        .debounceClickable {
                            navController.navigate(MovieDetailRouteScreen(item))
                        },
                    onClickedFavorite = { movie, favorite ->
                        viewModel.updateFavoriteMovie(movie, favorite)
                    }
                )
            }
        },
        modifier = modifier
    )
}

@Composable
fun CollapseTopBarContainer(
    modifier: Modifier = Modifier,
    collapsedTopBar: @Composable () -> Unit,
    extendedTopBar: @Composable () -> Unit,
    listState: LazyListState = rememberLazyListState(),
    lazyColumnItem: LazyListScope.() -> Unit,
) {
    Box(modifier = modifier) {
        collapsedTopBar()
        LazyColumn(
            modifier = modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            state = listState
        ) {
            item {
                extendedTopBar()
            }
            lazyColumnItem()
        }
    }
}

/*@Composable
fun BoxParallaxContainer(
    title: String,
    posterUrl: String,
    movie: MovieModel,
    movieList: List<MovieModel>,
    modifier: Modifier = Modifier
) {

    val overlapHeightPx = with(LocalDensity.current) {
        EXPANDED_TOP_BAR_HEIGHT.toPx() - COLLAPSED_TOP_BAR_HEIGHT.toPx()
    }

    val listState = rememberLazyListState()

    val isCollapsed: Boolean by remember {
        derivedStateOf {
            val isFirstItemHidden = listState.firstVisibleItemScrollOffset > overlapHeightPx
            isFirstItemHidden || listState.firstVisibleItemIndex > 0
        }
    }

    Box(modifier = modifier) {
        CollapsedTopBar(
            title = title,
            isCollapsed = isCollapsed,
            modifier = Modifier.zIndex(2f)
        )
        LazyColumn(
            modifier = modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            state = listState
        ) {
            item {
                ExpandedTopBar(title = title, posterUrl = posterUrl)
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                MovieDetail(
                    movie = movie,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                )
            }
            item {
                Spacer(modifier = Modifier.height(32.dp))
            }
            items(movieList, key = {
                it.id
            }) { item ->
                ConstraintHorizontalPoster(
                    movie = item,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}*/

@Composable
fun ExpandedTopBar(
    title: String,
    posterUrl: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .background(Color.White)
            .fillMaxWidth()
            .height(EXPANDED_TOP_BAR_HEIGHT),
        contentAlignment = Alignment.BottomStart
    ) {
        CoilImage(
            url = posterUrl,
            modifier = Modifier
                .fillMaxSize(),
            coilImageModifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .padding(vertical = 10.dp)
                .clip(
                    RoundedCornerShape(
                        topEnd = 8.dp,
                        bottomEnd = 8.dp
                    )
                )
                .background(Color.White)
        ) {
            Text(
                text = title,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 32.dp),
                style = MaterialTheme.typography.titleLarge.copy(
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

@Composable
fun CollapsedTopBar(
    title: String,
    isCollapsed: Boolean,
    modifier: Modifier = Modifier
) {
    val color: Color by animateColorAsState(
        if (isCollapsed) {
            Color.White
        } else {
            Color.Transparent
        }
    )
    Box(
        modifier = modifier
            .background(color)
            .fillMaxWidth()
            .height(COLLAPSED_TOP_BAR_HEIGHT)
            .padding(16.dp),
        contentAlignment = Alignment.BottomStart
    ) {
        AnimatedVisibility(visible = isCollapsed) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge.copy(
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

@Composable
fun MovieDetail(
    movie: MovieModel,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(
        modifier
            .fillMaxWidth()
    ) {
        val startGuideline = createGuidelineFromStart(16.dp)
        val endGuideline = createGuidelineFromEnd(16.dp)

        val (rating, voting, releaseDate, duration, language, titleDescription, detailDescription) = createRefs()

        Text(
            text = "Rating: ${movie.rating}",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.constrainAs(rating) {
                width = Dimension.fillToConstraints
                linkTo(start = startGuideline, end = endGuideline)
            })

        Text(
            text = "${movie.voting} Voting",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.constrainAs(voting) {
                width = Dimension.wrapContent
                end.linkTo(endGuideline)
                baseline.linkTo(rating.baseline)
            })

        Text(
            text = "Release Date: ${movie.releaseDate}",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.constrainAs(releaseDate) {
                width = Dimension.fillToConstraints
                linkTo(start = startGuideline, end = endGuideline)
                top.linkTo(rating.bottom, margin = 12.dp)
            }
        )

        Text(
            text = "Duration: ${movie.duration}",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.constrainAs(duration) {
                width = Dimension.fillToConstraints
                linkTo(start = startGuideline, end = endGuideline)
                top.linkTo(releaseDate.bottom, margin = 12.dp)
            }
        )

        Text(
            text = "Language: TH/EN",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.constrainAs(language) {
                width = Dimension.fillToConstraints
                linkTo(start = startGuideline, end = endGuideline)
                top.linkTo(duration.bottom, margin = 12.dp)
            }
        )

        Text(
            text = "Movie Description",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.constrainAs(titleDescription) {
                width = Dimension.fillToConstraints
                linkTo(start = startGuideline, end = endGuideline)
                top.linkTo(language.bottom, margin = 30.dp)
            }
        )

        ExpandedText(
            text = movie.description,
            collapsedMaxLine = 4,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.constrainAs(detailDescription) {
                width = Dimension.fillToConstraints
                linkTo(start = startGuideline, end = endGuideline)
                top.linkTo(titleDescription.bottom, margin = 30.dp)
            }
        )
    }
}

@Preview
@Composable
private fun MovieDetailPreview() {
    MovieDetail(
        movie = MovieModel(
            id = "1",
            name = "Hello world",
            rating = "100/1000",
            description = "The travels of a lone bounty hunter in the outer reaches of the galaxy"
        )
    )
}

@Preview
@Composable
private fun ExpandedTopBarPreview() {
    ExpandedTopBar(
        posterUrl = "",
        title = "Hello world",
        modifier = Modifier
    )

}