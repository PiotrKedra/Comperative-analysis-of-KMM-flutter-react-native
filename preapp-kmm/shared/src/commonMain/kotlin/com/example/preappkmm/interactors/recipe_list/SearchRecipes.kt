package com.example.preappkmm.interactors.recipe_list

import com.example.preappkmm.datasource.cache.RecipeCache
import com.example.preappkmm.datasource.network.RecipeService
import com.example.preappkmm.domain.model.Recipe
import com.example.preappkmm.domain.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRecipes(
    private val recipeService: RecipeService,
    private val recipeCache: RecipeCache
) {
    fun execute(page: Int, query: String): Flow<DataState<List<Recipe>>> = flow {

        emit(DataState.loading<List<Recipe>>())
        try {
            val recipe = recipeService.search(
                page = page,
                query = query
            )
            recipeCache.insert(recipe)

            val cacheResult = if(query.isBlank()) {
                recipeCache.getAll(page=page)
            } else {
                recipeCache.search(
                    query=query,
                    page=page
                )
            }
            emit(DataState.data<List<Recipe>>(message = null, data = cacheResult))
        } catch (e: Exception) {
            emit(DataState.error<List<Recipe>>(message = e.message ?: "Unknown Error"))
        }
    }

}