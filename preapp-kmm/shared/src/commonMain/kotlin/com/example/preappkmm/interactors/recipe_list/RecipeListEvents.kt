package com.example.preappkmm.interactors.recipe_list

import com.example.preappkmm.presentation.recipe_list.FoodCategory

sealed class RecipeListEvents {
    object LoadRecipes: RecipeListEvents()

    object NextPage: RecipeListEvents()

    object NewSearch:RecipeListEvents()

    data class OnUpdateQuery(val query: String): RecipeListEvents()

    data class OnSelectCategory(val category: FoodCategory): RecipeListEvents()

}