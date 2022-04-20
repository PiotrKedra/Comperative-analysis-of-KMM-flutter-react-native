package com.example.preappkmm.android.presentation.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.example.preappkmm.android.presentation.recipe_detail.RecipeDetailScreen
import com.example.preappkmm.android.presentation.recipe_detail.RecipeDetailViewModel
import com.example.preappkmm.android.presentation.recipe_list.RecipeListScreen
import com.example.preappkmm.android.presentation.recipe_list.RecipeListViewModel


const val RECIPE_ID = "recipeId"

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.RecipeList.route){
        composable(route = Screen.RecipeList.route){ navBackStackEntry ->
            val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
            val viewModel: RecipeListViewModel = viewModel("RecipeListViewModel", factory)

            RecipeListScreen(
                state = viewModel.state.value,
                onClickRecipeListItem = { recipeId ->
                    navController.navigate(Screen.RecipeDetail.route + "/$recipeId")
                }
            )
        }
        composable(
            route = Screen.RecipeDetail.route + "/{$RECIPE_ID}",
            arguments = listOf(navArgument(RECIPE_ID){
                type = NavType.IntType
            })
        ){ navBackStackEntry ->
            val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
            val viewModel: RecipeDetailViewModel = viewModel("RecipeDetailViewModel", factory)
            RecipeDetailScreen(recipe = viewModel.recipe.value)
        }
    }
}