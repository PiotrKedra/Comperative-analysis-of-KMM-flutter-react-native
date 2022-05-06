package com.example.kmm_network.android.presentation.user_detail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun UserDetailScreen(
    userId: Int?,
) {
    if (userId == null) {
        Text("Error (no id)")
    } else {
        Text("User id: $userId")
    }
}