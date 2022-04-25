package com.example.preappkmm.android.presentation.recipe_list

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import com.example.preappkmm.android.presentation.recipe_list.components.RecipeList
import com.example.preappkmm.android.presentation.recipe_list.components.SearchAppBar
import com.example.preappkmm.android.presentation.theme.AppTheme
import com.example.preappkmm.interactors.recipe_list.RecipeListEvents
import com.example.preappkmm.presentation.recipe_list.RecipeListState

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun RecipeListScreen(
    state: RecipeListState,
    onTriggerEvent: (RecipeListEvents) -> Unit,
    onClickRecipeListItem: (Int) -> Unit,
){
    AppTheme(displayProgressBar = state.isLoading) {

        Scaffold(
            topBar = {
                SearchAppBar(
                    query = state.query,
                    onQueryChange = {},
                    onExecuteSearch = {}
                )
            },
        ) {
            println(it)
            RecipeList(
                loading = state.isLoading,
                recipes = state.recipes,
                page = state.page,
                onTriggerNextPage = {
                    onTriggerEvent(RecipeListEvents.NextPage)
                },
                onClickRecipeListItem = onClickRecipeListItem
            )
        }
    }
}