package com.example.preappkmm.interactors.recipe_list

import com.example.preappkmm.datasource.RecipeService
import com.example.preappkmm.domain.model.Recipe
import com.example.preappkmm.domain.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRecipes(
    private val recipeService: RecipeService
) {
    fun execute(page: Int, query: String): Flow<DataState<List<Recipe>>> = flow {

        emit(DataState.loading<List<Recipe>>())
        try {
            val recipe = recipeService.search(
                page = page,
                query = query
            )
            emit(DataState.data<List<Recipe>>(message = null, data = recipe))
        } catch (e: Exception) {
            emit(DataState.error<List<Recipe>>(message = e.message ?: "Unknown Error"))
        }
    }

}