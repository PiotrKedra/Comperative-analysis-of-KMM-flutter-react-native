package com.example.preappkmm.android.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.preappkmm.android.presentation.theme.Main300

const val IMAGE_HEIGHT = 130

@Composable
fun RecipeImage(
    url: String,
    contentDescription: String,
) {
    Box{
        SubcomposeAsyncImage (
            model = url,
            contentDescription = contentDescription,
            modifier = Modifier
                .fillMaxWidth()
                .height(IMAGE_HEIGHT.dp),
            contentScale = ContentScale.Crop,
            loading = {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(IMAGE_HEIGHT.dp)
                    .background(Main300)
                ) {
                    // empty for white background
                }
            }
        )
    }
}