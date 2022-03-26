package com.example.preappkmm.android.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.example.preappkmm.android.presentation.recipe_detail.RecipeDetailScreen
import com.example.preappkmm.android.presentation.recipe_list.RecipeListScreen


const val RECIPE_ID = "recipeId"

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.RecipeList.route){
        composable(route = Screen.RecipeList.route){ navBackStackEntry ->
            RecipeListScreen(
                onSelectedRecipe = { recipeId ->
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
            RecipeDetailScreen(recipeId = navBackStackEntry.arguments?.getInt(RECIPE_ID))
        }
    }
}