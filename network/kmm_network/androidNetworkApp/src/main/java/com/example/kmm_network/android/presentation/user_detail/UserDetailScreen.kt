package com.example.kmm_network.android.presentation.user_detail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.kmm_network.domain.model.User

@Composable
fun UserDetailScreen(
    user: User?,
) {
    if (user == null) {
        Text("Error (no id)")
    } else {
        Text("User email: ${user.email}")
    }
}