package com.example.kmm_network.android.presentation.navigation

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.kmm_network.android.presentation.modify_user.ModifyUserScreen
import com.example.kmm_network.android.presentation.modify_user.ModifyUserViewModel
import com.example.kmm_network.android.presentation.user_detail.UserDetailScreen
import com.example.kmm_network.android.presentation.user_detail.UserDetailViewModel
import com.example.kmm_network.android.presentation.user_list.UserListScreen
import com.example.kmm_network.android.presentation.user_list.UserListViewModel
import com.example.kmm_network.presentation.user_list.UserListEvents

@Composable
fun Navigation() {
    val navController = rememberNavController()

    var shouldRefresh:Boolean by remember { mutableStateOf(false) }

    NavHost(
        navController = navController,
        startDestination = Screen.UserList.route
    ) {

        composable(route = Screen.UserList.route) {

            val viewModel = hiltViewModel<UserListViewModel>()
            UserListScreen(
                state = viewModel.state.value,
                shouldRefresh = shouldRefresh,
                refresh = {
                    shouldRefresh = false
                    viewModel.onTriggerEvent(UserListEvents.Refresh)
                },
                onTriggerEvent = viewModel::onTriggerEvent,
                onSelectedUser = { userId ->
                    navController.navigate(Screen.UserDetail.route + "/$userId")
                },
                onClickAddNewUser = {
                    navController.navigate(Screen.CreateUser.route)
                }
            )
        }

        composable(
            route = Screen.UserDetail.route + "/{userId}",
            arguments = listOf(navArgument("userId") {
                type = NavType.IntType
            })
        ) {
            // thanks to this hilt maintain for instance cases, when we open two same screen, it keeps the history of them perfectly
            // (hilt gets navBackStackEntry under the hood, so we can use all arguments in our viewModel)
            // https://developer.android.com/jetpack/compose/libraries#hilt
            val viewModel = hiltViewModel<UserDetailViewModel>()
            UserDetailScreen(
                user = viewModel.user.value,
                deleteUser = { userId ->
                    viewModel.deleteUser(userId)
                    shouldRefresh = true
                    navController.popBackStack()
                },
                onClickUpdateUser = { userId ->
                    navController.navigate(Screen.UpdateUser.route + "/$userId")
                }
            )
        }

        composable(
            route = Screen.CreateUser.route
        ) {
            val viewModel = hiltViewModel<ModifyUserViewModel>()
            ModifyUserScreen(
                state = viewModel.state.value,
                modifyUser = { viewModel.createUser(it) },
                goBack = {
                    navController.popBackStack()
                    shouldRefresh=true
                }
            )
        }

        composable(
            route = Screen.UpdateUser.route + "/{userId}",
            arguments = listOf(navArgument("userId") {
                type = NavType.IntType
            })
        ) {
            val viewModel = hiltViewModel<ModifyUserViewModel>()
            ModifyUserScreen(
                state = viewModel.state.value,
                modifyUser = { viewModel.update(it) },
                goBack = {
                    navController.popBackStack(Screen.UserList.route, false)
                    shouldRefresh=true
                },
                isCreate = false
            )
        }
    }
}