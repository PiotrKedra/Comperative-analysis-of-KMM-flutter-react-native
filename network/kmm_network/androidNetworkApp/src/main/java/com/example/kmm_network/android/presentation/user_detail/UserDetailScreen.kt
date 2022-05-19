package com.example.kmm_network.android.presentation.user_detail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.kmm_network.android.presentation.user_detail.components.UserDetails
import com.example.kmm_network.android.presentation.user_list.UserCard
import com.example.kmm_network.android.theme.AppTheme
import com.example.kmm_network.domain.model.User

@Composable
fun UserDetailScreen(
    user: User?,
    deleteUser: (Int) -> Unit,
    onClickUpdateUser: (Int) -> Unit
) {
    AppTheme(displayProgressBar = false) {
        if (user == null) {
            Text("Error (no id)")
        } else {
            UserDetails(
                user = user,
                deleteUser = deleteUser,
                onClickUpdateUser = onClickUpdateUser
            )
        }
    }
}