package com.example.kmm_network.android.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.kmm_network.android.presentation.user_detail.UserDetailScreen
import com.example.kmm_network.android.presentation.user_detail.UserDetailViewModel
import com.example.kmm_network.android.presentation.user_list.UserListScreen
import com.example.kmm_network.android.presentation.user_list.UserListViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.UserList.route
    ) {
        composable(route = Screen.UserList.route) { navBackStackEntry ->

            val viewModel = hiltViewModel<UserListViewModel>()

            UserListScreen(
                state = viewModel.state.value,
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
            // thanks to this hilt maintain for instance cases, when we open two same screen, it keeps the history of them perfectly
            // (hilt gets navBackStackEntry under the hood, so we can use all arguments in our viewModel)
            // https://developer.android.com/jetpack/compose/libraries#hilt
            val viewModel = hiltViewModel<UserDetailViewModel>()
            UserDetailScreen(user = viewModel.user.value)
        }
    }
}