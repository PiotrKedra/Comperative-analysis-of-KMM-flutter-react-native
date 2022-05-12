package com.example.kmm_network.presentation.user_list

import com.example.kmm_network.domain.model.User

data class UserListState(
    val isLoading: Boolean = false,
    val page: Int = 1,
    val users: List<User> = listOf()
)