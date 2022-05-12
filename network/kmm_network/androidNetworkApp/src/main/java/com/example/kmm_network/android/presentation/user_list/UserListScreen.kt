package com.example.kmm_network.android.presentation.user_list

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.kmm_network.android.presentation.user_list.components.UserList
import com.example.kmm_network.android.theme.AppTheme
import com.example.kmm_network.presentation.user_list.UserListState

@Composable
fun UserListScreen(
    state: UserListState,
    onSelectedUser: (Int) -> Unit,
) {
    AppTheme(displayProgressBar = state.isLoading) {
        Column (
            modifier = Modifier.fillMaxSize()
        ) {
            UserList(
                isLoading = state.isLoading,
                users = state.users,
                onSelectedUser = onSelectedUser
            )
        }

    }
}