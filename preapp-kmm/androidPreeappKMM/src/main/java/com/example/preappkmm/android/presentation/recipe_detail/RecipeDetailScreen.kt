package com.example.preappkmm.android.presentation.recipe_detail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.preappkmm.domain.model.Recipe

@Composable
fun RecipeDetailScreen(
    recipe: Recipe?,
){
    if (recipe == null) {
        Text(text = "Some error (no recipe id)")
    } else {
        Text(text = "Recipe details (recipe ID: ${recipe.title})")
    }
}