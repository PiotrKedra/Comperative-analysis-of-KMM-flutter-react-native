package com.example.kmm_network.android.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage

const val SMALL_IMG_SIZE = 130

@Composable
fun UserSmallImage(
    url: String,
    contentDescription: String,
) {
    SubcomposeAsyncImage(
        model = url,
        contentDescription = contentDescription,
        modifier = Modifier
            .width(SMALL_IMG_SIZE.dp)
            .height(SMALL_IMG_SIZE.dp)
            .clip(MaterialTheme.shapes.large),
        contentScale = ContentScale.Crop,
        loading = {
            Box(modifier = Modifier
                .width(SMALL_IMG_SIZE.dp)
                .height(SMALL_IMG_SIZE.dp)
                .clip(MaterialTheme.shapes.large)
                .background(Color.LightGray)
            ) {
                // empty for white background
            }
        }
    )
}