package com.example.preappkmm.presentation.recipe_list

import com.example.preappkmm.domain.model.Recipe
import com.example.preappkmm.domain.util.Queue

data class RecipeListState(
    val isLoading: Boolean = false,
    val page: Int = 1,
    val query: String = "",
    val selectedCategory: FoodCategory? = null,
    val recipes: List<Recipe> = listOf(),
    val queue: Queue<String> = Queue(mutableListOf())
)