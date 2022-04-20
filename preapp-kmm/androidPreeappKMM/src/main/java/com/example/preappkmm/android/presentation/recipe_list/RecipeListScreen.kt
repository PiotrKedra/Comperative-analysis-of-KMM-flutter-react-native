package com.example.preappkmm.android.presentation.recipe_list

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import com.example.preappkmm.android.presentation.recipe_list.components.RecipeList
import com.example.preappkmm.android.presentation.theme.AppTheme
import com.example.preappkmm.presentation.recipe_list.RecipeListState

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun RecipeListScreen(
    state: RecipeListState,
    onClickRecipeListItem: (Int) -> Unit,
){
    AppTheme(displayProgressBar = state.isLoading) {
        RecipeList(
            loading = state.isLoading,
            recipes = state.recipes,
            onClickRecipeListItem = onClickRecipeListItem
        )
    }
}