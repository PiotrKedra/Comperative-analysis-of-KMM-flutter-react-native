package com.example.preappkmm.android.presentation.recipe_list.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun LoadingRecipeListShimmer(
    imageHeight: Dp,
    padding: Dp = 16.dp,
) {
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        val cardWidthPx = with(LocalDensity.current){(maxWidth - (padding*2)).toPx()}
        val gradientWidth: Float = (.2f * cardWidthPx)

        val infiniteTransition = rememberInfiniteTransition()
        val xCardShimmer = infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = (cardWidthPx + gradientWidth),
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 500,
                    easing = FastOutLinearInEasing,
                    delayMillis = 200,
                ),
                repeatMode = RepeatMode.Restart
            )
        )

        val colors = listOf(
            Color.LightGray.copy(alpha = .4f),
            Color.LightGray.copy(alpha = .2f),
            Color.LightGray.copy(alpha = .4f),
        )
        
        LazyColumn{
            items(8) {
                ShimmerRecipeCardItem(
                    colors = colors,
                    xShimmer = xCardShimmer.value,
                    cardHeight = imageHeight,
                    gradientWidth = gradientWidth,
                )
            }
        }
    }
}