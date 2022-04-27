package com.example.preappkmm.android.presentation.recipe_detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.preappkmm.android.presentation.navigation.RECIPE_ID
import com.example.preappkmm.interactors.recipe_details.GetRecipe
import com.example.preappkmm.presentation.recipe_detail.RecipeDetailEvents
import com.example.preappkmm.presentation.recipe_detail.RecipeDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@OptIn(ExperimentalStdlibApi::class)
@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getRecipe: GetRecipe
): ViewModel() {
    val state: MutableState<RecipeDetailState> = mutableStateOf(RecipeDetailState())

    init {
        savedStateHandle.get<Int>(RECIPE_ID)?.let { recipeId ->
            onTriggerEvent(RecipeDetailEvents.GetRecipe(recipeId))
        }
    }

    fun onTriggerEvent(event: RecipeDetailEvents) {
        when(event) {
            is RecipeDetailEvents.GetRecipe -> {
                loadRecipe(recipeId = event.recipeId)
            }
            else -> {
                handleError("Invalid event")
            }
        }
    }

    private fun loadRecipe(recipeId: Int) {
        getRecipe.execute(recipeId = recipeId).onEach { dataState ->
            this.state.value=state.value.copy(isLoading = dataState.isLoading)


            dataState.data?.let { recipe ->
                this.state.value=state.value.copy(recipe =recipe)
            }

            dataState.message?.let { message ->
                handleError("RecipeDetail Error: $message")
            }
        }.launchIn(viewModelScope)
    }

    private fun handleError(errorMsg: String) {
        println(errorMsg)
    }
}