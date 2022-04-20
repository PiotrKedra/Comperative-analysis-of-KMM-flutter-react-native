package com.example.preappkmm.android.presentation.recipe_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.preappkmm.android.presentation.components.RecipeImage
import com.example.preappkmm.android.presentation.theme.Black1
import com.example.preappkmm.domain.model.Recipe

@Composable
fun RecipeCard(
    recipe: Recipe,
    onClick: () -> Unit,
) {
    Card(
        shape = MaterialTheme.shapes.large,
        modifier = Modifier
            .padding(top=16.dp, start=16.dp, end=16.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = 8.dp
    ) {
        Column{
            RecipeImage(url = recipe.featuredImage, contentDescription = recipe.title)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 12.dp, start = 12.dp, end = 12.dp)
            ) {
                Text(
                    text = recipe.title,
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .wrapContentWidth(Alignment.Start),
                    style = MaterialTheme.typography.h4
                )
                Text(
                    text=recipe.rating.toString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.End)
                        .align(Alignment.Top),
                    style = MaterialTheme.typography.h4,
                    color = Black1
                )
            }
        }
    }
}