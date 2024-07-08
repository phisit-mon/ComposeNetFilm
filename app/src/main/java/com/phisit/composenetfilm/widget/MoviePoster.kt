package com.phisit.composenetfilm.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Comment
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.phisit.composenetfilm.domain.MovieModel
import com.phisit.composenetfilm.utils.CoilImage
import com.phisit.composenetfilm.`ีutils`.clickableWithRippleEffect

@Composable
fun VerticalPoster(
    movie: MovieModel,
    modifier: Modifier = Modifier,
    onClickedFavorite: (MovieModel, Boolean) -> Unit = { _, _ -> },
    onClickComment: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CoilImage(
            url = movie.posterUrl.orEmpty(),
            modifier = Modifier
                .weight(1f),
            coilImageModifier = Modifier
                .clip(RoundedCornerShape(12.dp))
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = movie.name,
            textAlign = TextAlign.Start,
            maxLines = 2,
            minLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyMedium
                .copy(
                    fontWeight = FontWeight.Bold
                ),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Start)
        )
        movie.apply {
            Feedback(
                favorite = isFavorite,
                rating = rating,
                comment = comment,
                onClickedFavorite = { favorite ->
                    onClickedFavorite(movie, favorite)
                },
                onClickComment = onClickComment,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(vertical = 4.dp)
            )
        }
    }
}

@Composable
fun HorizontalPoster(
    movie: MovieModel,
    modifier: Modifier = Modifier,
    onClickFavorite: (value: Boolean) -> Unit = {},
    onClickComment: () -> Unit = {}
) {
    Row(modifier) {
        CoilImage(
            url = movie.posterUrl.orEmpty(),
            modifier = Modifier
                .width(160.dp)
                .height(250.dp)
                .fillMaxHeight(),
            coilImageModifier = Modifier.clip(RoundedCornerShape(12.dp))
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = movie.name,
                textAlign = TextAlign.Start,
                maxLines = 2,
                minLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium
                    .copy(
                        fontWeight = FontWeight.Bold
                    ),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = movie.description,
                textAlign = TextAlign.Start,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium
                    .copy(color = Color.DarkGray),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Start)
            )
        }
    }
}

@Composable
fun ConstraintHorizontalPoster(
    movie: MovieModel,
    modifier: Modifier = Modifier,
    onClickedFavorite: (MovieModel, Boolean) -> Unit = { _, _ -> },
    onClickComment: (MovieModel) -> Unit = {},
) {
    ConstraintLayout(modifier) {
        val (poster, title, description, feedback) = createRefs()
        CoilImage(
            url = movie.posterUrl.orEmpty(),
            modifier = Modifier
                .width(150.dp)
                .height(200.dp)
                .constrainAs(poster) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                },
            coilImageModifier = Modifier.clip(RoundedCornerShape(12.dp)),
        )

        Text(
            text = movie.name,
            textAlign = TextAlign.Start,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyMedium
                .copy(fontWeight = FontWeight.Bold),
            modifier = Modifier
                .constrainAs(title) {
                    top.linkTo(parent.top)
                    start.linkTo(poster.end, margin = 8.dp)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                    height = Dimension.wrapContent
                }
        )

        Text(
            text = movie.description,
            textAlign = TextAlign.Start,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyMedium
                .copy(color = Color.DarkGray),
            modifier = Modifier
                .constrainAs(description) {
                    linkTo(start = poster.end, end = parent.end, startMargin = 10.dp)
                    linkTo(top = title.bottom, bottom = feedback.top, topMargin = 10.dp, bias = 0f)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }
        )

        Feedback(
            favorite = movie.isFavorite,
            rating = movie.rating,
            comment = movie.comment,
            onClickedFavorite = { favorite ->
                onClickedFavorite(movie, favorite)
            },
            onClickComment = {},
            modifier = Modifier.constrainAs(feedback) {
                linkTo(start = description.start, end = description.end)
                top.linkTo(description.bottom, margin = 8.dp)
                width = Dimension.fillToConstraints
                height = Dimension.wrapContent
            }
        )
    }
}

@Composable
fun Feedback(
    favorite: Boolean,
    rating: String,
    comment: String,
    onClickedFavorite: (favorite: Boolean) -> Unit,
    onClickComment: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = if (favorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
            contentDescription = "Add to favorite",
            tint = Color.Red,
            modifier = Modifier
                .size(24.dp)
                .clickableWithRippleEffect {
                    onClickedFavorite.invoke(!favorite)
                }
        )
        if (rating.isNotEmpty()) {
            Spacer(modifier = Modifier.padding(start = 4.dp))
            Text(
                text = rating,
                style = MaterialTheme.typography.labelLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        if (comment.isNotEmpty()) {
            Row(
                modifier = Modifier
                    .clickableWithRippleEffect(onClickComment)
            ) {
                Spacer(modifier = Modifier.padding(start = 6.dp))
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Comment,
                    contentDescription = "Comment",
                    tint = Color.LightGray,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.padding(start = 4.dp))
                Text(
                    text = comment,
                    style = MaterialTheme.typography.labelLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun UserFeedbackPreview() {
    Feedback(
        favorite = true,
        rating = "8/10",
        comment = "200k",
        onClickedFavorite = {},
        onClickComment = {}
    )
}

@Preview(showBackground = true)
@Composable
private fun VerticalPosterPreview() {
    VerticalPoster(
        movie = MovieModel(
            id = "12",
            name = "Hello world",
            initialFavorite = true,
            rating = "8/10",
            comment = "200k"
        ),
        modifier = Modifier
            .width(170.dp)
            .height(290.dp)
    )
}

@Preview(showBackground = true)
@Composable
private fun ConstraintHorizontalPosterPreview() {
    ConstraintHorizontalPoster(
        movie = MovieModel(
            "1",
            "Hello world Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry'",
            "Lorem Ipsum is",
            "https://www.washingtonpost.com/graphics/2019/entertainment/oscar-nominees-movie-poster-design/img/1800/star.jpg",
            initialFavorite = true, rating = "8/10", comment = "200k"
        ),
        onClickedFavorite = { _, _ -> },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    )
}

/*
@Preview(showBackground = true)
@Composable
private fun HorizontalPosterPreview() {
    HorizontalPoster(
        movie = MovieModel(
            "1",
            "Hello world Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry'",
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
            "https://www.washingtonpost.com/graphics/2019/entertainment/oscar-nominees-movie-poster-design/img/1800/star.jpg",
            isFavorite = true, rating = "8/10", comment = "200k"
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    )
}*/
