package com.example.preappkmm.interactors.recipe_details

import com.example.preappkmm.datasource.cache.RecipeCache
import com.example.preappkmm.datasource.network.RecipeService
import com.example.preappkmm.domain.model.Recipe
import com.example.preappkmm.domain.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetRecipe(
    private val recipeCache: RecipeCache
) {
    fun execute(recipeId: Int): Flow<DataState<Recipe>> = flow {
        emit(DataState.loading<Recipe>())
        delay(2000)
        try {
            val recipe = recipeCache.get(recipeId)
            emit(DataState.data<Recipe>(message = null, data = recipe))
        } catch (e: Exception) {
            emit(DataState.error<Recipe>(message = e.message ?: "Unknown Error"))
        }
    }
}