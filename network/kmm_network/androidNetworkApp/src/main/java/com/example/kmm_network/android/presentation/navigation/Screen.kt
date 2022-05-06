package com.example.kmm_network.android.presentation.navigation

sealed class Screen(
    val route: String
) {
    object UserList: Screen("userScreen")
    object UserDetail: Screen("userDetailsScreen")
}