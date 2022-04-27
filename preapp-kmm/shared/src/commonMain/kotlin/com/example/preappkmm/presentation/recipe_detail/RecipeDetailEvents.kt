package com.example.preappkmm.presentation.recipe_detail

sealed class RecipeDetailEvents {
    data class GetRecipe(val recipeId: Int): RecipeDetailEvents()
}
