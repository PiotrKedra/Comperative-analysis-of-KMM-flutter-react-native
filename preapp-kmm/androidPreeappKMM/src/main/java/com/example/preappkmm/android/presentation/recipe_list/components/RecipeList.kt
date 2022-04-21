package com.example.preappkmm.android.presentation.recipe_list.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.preappkmm.android.presentation.components.IMAGE_HEIGHT
import com.example.preappkmm.domain.model.Recipe

@Composable
fun RecipeList(
    loading: Boolean,
    recipes: List<Recipe>,
    onClickRecipeListItem: (Int) -> Unit
) {
    if(loading && recipes.isEmpty()) {
        LoadingRecipeListShimmer(imageHeight = IMAGE_HEIGHT.dp)
    } else if(recipes.isEmpty()) {
        // notginh to show
        println("#$# nothing to show")
    } else {
        println("#$# lazy column")

        LazyColumn(){
            itemsIndexed(
                items = recipes
            ) { index, recipe ->
                RecipeCard(
                    recipe = recipe,
                    onClick = { onClickRecipeListItem(recipe.id)}
                )
            }
        }
    }
}