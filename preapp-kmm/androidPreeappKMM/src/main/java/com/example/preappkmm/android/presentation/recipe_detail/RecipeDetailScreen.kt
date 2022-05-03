package com.example.preappkmm.android.presentation.recipe_detail

import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.preappkmm.android.presentation.components.IMAGE_HEIGHT
import com.example.preappkmm.android.presentation.recipe_detail.components.LoadingRecipeShimmer
import com.example.preappkmm.android.presentation.recipe_detail.components.RecipeView
import com.example.preappkmm.android.presentation.theme.AppTheme
import com.example.preappkmm.domain.model.Recipe
import com.example.preappkmm.presentation.recipe_detail.RecipeDetailEvents
import com.example.preappkmm.presentation.recipe_detail.RecipeDetailState

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalStdlibApi
@Composable
fun RecipeDetailScreen(
    state: RecipeDetailState,
    onTriggerEvent: (RecipeDetailEvents) -> Unit,
){
    AppTheme(
        displayProgressBar = state.isLoading,
        dialogQueue = state.queue,
        onRemoveHeadMessageFromQueue = {
            onTriggerEvent(RecipeDetailEvents.OnRemoveHeadMessageFromQueue)
        }
    ) {
        if (state.recipe == null && state.isLoading) {
            LoadingRecipeShimmer(imageHeight = IMAGE_HEIGHT.dp)
        } else if (state.recipe == null) {
            Text(
                text = "We were unable to retrieve the details for this recipe..",
                modifier = Modifier.padding(16.dp)
            )
        } else {
            RecipeView(recipe = state.recipe!!)
        }
    }
}