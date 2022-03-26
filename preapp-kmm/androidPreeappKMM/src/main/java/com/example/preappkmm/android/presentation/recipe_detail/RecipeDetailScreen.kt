package com.example.preappkmm.android.presentation.recipe_detail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun RecipeDetailScreen(
    recipeId: Int?,
){
    if (recipeId == null) {
        Text(text = "Some error (no recipe id)")
    } else {
        Text(text = "Recipe details (recipe ID: $recipeId)")
    }
}