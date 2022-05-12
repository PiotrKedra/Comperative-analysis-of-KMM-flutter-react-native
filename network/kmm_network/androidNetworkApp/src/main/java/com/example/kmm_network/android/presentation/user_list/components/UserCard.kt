package com.example.kmm_network.android.presentation.user_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kmm_network.android.presentation.components.UserSmallImage
import com.example.kmm_network.domain.model.User

const val LOREM_IPSUM = "Lorem Ipsum is simply dummy text of the printing and typesetting industry."

@Composable
fun UserCard(
    user: User,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = 0.dp
    ) {
        Row {
            UserSmallImage(url = user.avatar, contentDescription = user.email)
            Column(
                modifier = Modifier
                    .padding(start = 10.dp, top = 10.dp),
            ) {
                Text(
                    text = "${user.firstName} ${user.lastName}",
                    style = MaterialTheme.typography.h3
                )
                Text(
                    text = user.email,
                    style = MaterialTheme.typography.h5
                )
                Text(
                    text = LOREM_IPSUM,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(top = 10.dp)
                )
            }
        }
    }
}