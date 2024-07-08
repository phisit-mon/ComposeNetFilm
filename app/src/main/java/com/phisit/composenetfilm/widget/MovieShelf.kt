package com.phisit.composenetfilm.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.phisit.composenetfilm.domain.MovieModel
import com.phisit.composenetfilm.`ีutils`.debounceClickable

@Composable
fun MovieVerticalShelf(
    title: String,
    movies: List<MovieModel>,
    onClickItem: (MovieModel) -> Unit,
    modifier: Modifier = Modifier,
    onClickedFavorite: (MovieModel, Boolean) -> Unit = { _, _ -> },
    onClickComment: (MovieModel) -> Unit = {}
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge
                .copy(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(4.dp))
        LazyRow {
            items(movies, key = {
                it.id
            }) { movie ->
                VerticalPoster(
                    movie = movie,
                    modifier = Modifier
                        .width(170.dp)
                        .height(290.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .debounceClickable {
                            onClickItem(movie)
                        },
                    onClickedFavorite = onClickedFavorite,
                    onClickComment = {
                        onClickComment(movie)
                    }
                )
            }
        }
    }
}

@Composable
fun MovieHorizontalShelf(
    title: String,
    movies: List<MovieModel>,
    onClickItem: (MovieModel) -> Unit,
    modifier: Modifier = Modifier,
    onClickedFavorite: (MovieModel, Boolean) -> Unit = { _, _ -> },
    onClickComment: (MovieModel) -> Unit = {}
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge
                .copy(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(4.dp))
        /*        LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(space = 14.dp),
                ) {
                    items(movies, key = {
                        it.id
                    }) { movie ->
                        ConstraintHorizontalPoster(
                            movie = movie,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .clip(RoundedCornerShape(8.dp))
                                .clickableWithRippleEffect {
                                    onClickItem(movie)
                                },
                            onClickFavorite = { isClicked ->
                                onClickFavorite(isClicked)
                            },
                            onClickComment = {
                                onClickComment(movie)
                            }
                        )
                    }
                }*/
        Column(
            verticalArrangement = Arrangement.spacedBy(space = 50.dp),
        ) {
            movies.map { movie ->
                ConstraintHorizontalPoster(
                    movie = movie,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .clip(RoundedCornerShape(8.dp))
                        .debounceClickable {
                            onClickItem(movie)
                        },
                    onClickedFavorite = onClickedFavorite,
                    onClickComment = {
                        onClickComment(movie)
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MovieVerticalShelfPreview() {

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

    MovieVerticalShelf(
        title = "Coming Soon",
        movies = movieList,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 12.dp),
        onClickItem = {}
    )
}

@Preview(showBackground = true)
@Composable
private fun MovieHorizontalShelfPreview() {

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

    MovieHorizontalShelf(
        title = "Coming Soon",
        movies = movieList,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 12.dp),
        onClickItem = {}
    )
}