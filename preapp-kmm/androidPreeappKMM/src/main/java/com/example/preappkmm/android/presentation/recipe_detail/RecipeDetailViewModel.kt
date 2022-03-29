package com.example.preappkmm.android.presentation.recipe_detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.preappkmm.android.presentation.navigation.RECIPE_ID
import com.example.preappkmm.datasource.RecipeService
import com.example.preappkmm.domain.model.Recipe
import com.example.preappkmm.domain.util.DateTimeUtil
import com.example.preappkmm.interactors.recipe_details.GetRecipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalStdlibApi::class)
@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getRecipe: GetRecipe
): ViewModel() {
    val recipe: MutableState<Recipe?> = mutableStateOf(null)

    init {
        savedStateHandle.get<Int>(RECIPE_ID)?.let { recipeId ->
            loadRecipe(recipeId=recipeId)
        }
    }

    private fun loadRecipe(recipeId: Int) {
        getRecipe.execute(recipeId = recipeId).onEach { dataState ->
            println("RecipeListVM loading: ${dataState.isLoading}")

            dataState.data?.let { recipe ->
                println("RecipeListVM recipes: $recipe")
                this.recipe.value=recipe
            }

            dataState.message?.let { message ->
                println("RecipeListVM message: $message")
            }
        }.launchIn(viewModelScope)
    }
}