package com.example.preappkmm.datasource

import com.example.preappkmm.domain.model.Recipe

interface RecipeService {
    suspend fun search(page: Int, query: String, ): List<Recipe>
    suspend fun get(id: Int): Recipe
}