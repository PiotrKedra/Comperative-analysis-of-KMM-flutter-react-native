package com.example.kmm_network.presentation.user_list

import com.example.kmm_network.domain.model.User

data class UserListState(
    val isLoading: Boolean = false,
    val page: Int = 1,
    val users: List<User> = listOf()
) {
    // zero argument constructor is required for swift
    constructor(): this (
        isLoading = false,
        page = 1,
        users = listOf()
    )

    companion object {
        const val BASE_URL = "https://reqres.in/api/"
        const val PAGINATION_PAGE_SIZE = 6
    }
}