package com.example.preappkmm.android.presentation.recipe_detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.preappkmm.android.di.Dummy
import com.example.preappkmm.android.presentation.navigation.RECIPE_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val dummy: Dummy,
): ViewModel() {
    val recipeId: MutableState<Int?> = mutableStateOf(null)

    init {
        savedStateHandle.get<Int>(RECIPE_ID)?.let { recipeId ->
            this.recipeId.value = recipeId
        }
        println("##### Recipe Detail View Model: ${dummy.description()}")
    }
}