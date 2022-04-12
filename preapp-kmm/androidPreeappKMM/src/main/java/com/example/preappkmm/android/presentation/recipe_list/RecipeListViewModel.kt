package com.example.preappkmm.android.presentation.recipe_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.preappkmm.interactors.recipe_list.SearchRecipes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val searchRecipes: SearchRecipes
): ViewModel() {

    init {
        loadRecipes()
    }

    private fun loadRecipes() {
        searchRecipes.execute(
            page = 1,
            query = ""
        ).onEach { dataState ->
            println("RecipeListVM loading: ${dataState.isLoading}")

            dataState.data?.let { recipes ->
                println("RecipeListVM recipes: $recipes")
            }

            dataState.message?.let { message ->
                println("RecipeListVM message: $message")
            }

        }.launchIn(viewModelScope)
    }
}