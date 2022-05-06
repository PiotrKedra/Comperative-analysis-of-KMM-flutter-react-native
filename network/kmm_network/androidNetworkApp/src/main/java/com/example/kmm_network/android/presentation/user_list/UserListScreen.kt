package com.example.kmm_network.android.presentation.user_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun UserListScreen(
    onSelectedUser: (Int) -> Unit,
) {
    LazyColumn {
        items(100) { userId ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onSelectedUser(userId)
                    }
            ) {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = "User id: $userId"
                )
            }
        }
    }
}