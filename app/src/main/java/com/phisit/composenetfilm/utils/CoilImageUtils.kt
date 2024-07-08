package com.phisit.composenetfilm.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.phisit.composenetfilm.R

@Composable
fun CoilImage(
    url: String,
    modifier: Modifier = Modifier,
    coilImageModifier: Modifier = Modifier,
    contentScale:  ContentScale =  ContentScale.Crop
) {
    Box(modifier = modifier) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .crossfade(true)
                .memoryCachePolicy(CachePolicy.DISABLED)
                .data(url)
                .build(),
            placeholder = painterResource(R.drawable.glide_place_holder),
            error = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null,
            contentScale = contentScale,
            modifier = coilImageModifier
        )
    }
}