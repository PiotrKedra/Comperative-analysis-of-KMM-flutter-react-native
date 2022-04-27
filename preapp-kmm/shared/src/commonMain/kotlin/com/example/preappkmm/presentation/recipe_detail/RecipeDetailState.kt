package com.example.preappkmm.presentation.recipe_detail

import com.example.preappkmm.domain.model.Recipe

data class RecipeDetailState(
    val isLoading: Boolean = false,
    val recipe: Recipe? = null,
)
