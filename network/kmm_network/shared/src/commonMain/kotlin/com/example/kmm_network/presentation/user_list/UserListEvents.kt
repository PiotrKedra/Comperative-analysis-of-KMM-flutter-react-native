package com.example.kmm_network.presentation.user_list

sealed class UserListEvents {

    object LoadUsers: UserListEvents()

    object NextPage: UserListEvents()
    object Refresh: UserListEvents()
}