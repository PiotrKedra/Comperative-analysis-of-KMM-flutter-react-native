package com.example.preappkmm.presentation.recipe_detail

import com.example.preappkmm.domain.model.GenericMessageInfo
import com.example.preappkmm.domain.model.Recipe
import com.example.preappkmm.domain.util.Queue

data class RecipeDetailState(
    val isLoading: Boolean = false,
    val recipe: Recipe? = null,
    val queue: Queue<GenericMessageInfo> = Queue(mutableListOf())
)
