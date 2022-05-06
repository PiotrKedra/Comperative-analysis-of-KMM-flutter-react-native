package com.example.kmm_network.android.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.kmm_network.android.presentation.user_detail.UserDetailScreen
import com.example.kmm_network.android.presentation.user_list.UserListScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.UserList.route
    ) {
        composable(route = Screen.UserList.route) { navBackStackEntry ->

            UserListScreen(
                onSelectedUser = { userId ->
                    navController.navigate(Screen.UserDetail.route + "/$userId")
                }
            )
        }

        composable(
            route = Screen.UserDetail.route + "/{userId}",
            arguments = listOf(navArgument("userId") {
                type = NavType.IntType
            })
        ) { navBackStackEntry ->
            UserDetailScreen(userId = navBackStackEntry.arguments?.getInt("userId"))
        }
    }
}