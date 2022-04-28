package com.example.preappkmm.presentation.recipe_detail

import com.example.preappkmm.interactors.recipe_list.RecipeListEvents

sealed class RecipeDetailEvents {
    data class GetRecipe(val recipeId: Int): RecipeDetailEvents()

    object OnRemoveHeadMessageFromQueue: RecipeDetailEvents()

}
