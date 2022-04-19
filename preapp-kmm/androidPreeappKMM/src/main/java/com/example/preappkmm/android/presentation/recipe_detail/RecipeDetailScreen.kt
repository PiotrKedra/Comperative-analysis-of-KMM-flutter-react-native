package com.example.preappkmm.android.presentation.recipe_detail

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import com.example.preappkmm.android.presentation.components.RecipeImage
import com.example.preappkmm.android.presentation.recipe_list.components.RecipeCard
import com.example.preappkmm.android.presentation.theme.AppTheme
import com.example.preappkmm.domain.model.Recipe

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun RecipeDetailScreen(
    recipe: Recipe?,
){
    AppTheme(displayProgressBar = false) {
        if (recipe == null) {
            Text(text = "Some error (no recipe id)")
        } else {
            RecipeCard(
                recipe=recipe,
                onClick = {}
            )
        }
    }
}