package com.example.preappkmm.android.presentation.recipe_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerRecipeCardItem(
    colors: List<Color>,
    xShimmer: Float,
    cardHeight: Dp,
    gradientWidth: Float,
) {

    val brush = Brush.horizontalGradient(colors, startX = xShimmer - gradientWidth, endX = xShimmer)

    Card(
        shape = MaterialTheme.shapes.large,
        modifier = Modifier
            .padding(top=16.dp, start=16.dp, end=16.dp)
            .fillMaxWidth(),
        elevation = 8.dp
    ) {
        Column{
            Surface(shape = MaterialTheme.shapes.small){
                Spacer(
                    modifier = Modifier
                        .fillMaxSize()
                        .height(cardHeight)
                        .background(brush = brush)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 12.dp, start = 12.dp, end = 12.dp)
            ) {
                Surface(shape = MaterialTheme.shapes.small){
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .height(cardHeight/5)
                            .background(brush = brush)
                    )
                }

                Surface(shape = MaterialTheme.shapes.small){
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 26.dp)
                            .height(cardHeight/5)
                            .background(brush = brush)
                    )
                }
            }
        }
    }
}