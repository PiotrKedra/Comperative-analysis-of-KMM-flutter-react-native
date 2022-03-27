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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalStdlibApi::class)
@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val recipeService: RecipeService
): ViewModel() {
    val recipe: MutableState<Recipe?> = mutableStateOf(null)

    init {
        savedStateHandle.get<Int>(RECIPE_ID)?.let { recipeId ->
            viewModelScope.launch {
                recipe.value = recipeService.get(recipeId)
                println("KtorText ${recipe.value!!.title}")
                println("KtorText ${recipe.value!!.ingredients}")
                println("KtorText ${DateTimeUtil().humanizeDatetime(recipe.value!!.dateAdded)}")
            }
        }
    }
}