package com.example.kmm_network.android.presentation.user_list.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.kmm_network.android.presentation.components.MainTitleText
import com.example.kmm_network.android.presentation.user_list.UserCard
import com.example.kmm_network.datasource.network.UserServiceImpl.Companion.PAGINATION_PAGE_SIZE
import com.example.kmm_network.domain.model.User

@Composable
fun UserList(
    isLoading: Boolean,
    users: List<User>,
    page: Int,
    onTriggerNextPage: () -> Unit,
    onSelectedUser: (Int) -> Unit,
) {
    LazyColumn {

        item {
            MainTitleText(text = "Our users ")
        }

        if (isLoading && users.isEmpty()) {

        } else if (users.isEmpty()) {

        } else {
            itemsIndexed(
                items = users
            ) { index, user ->

                if ((index + 1) >= (page * PAGINATION_PAGE_SIZE) && !isLoading) {
                    onTriggerNextPage()
                }

                UserCard(user = user, onClick = {onSelectedUser(user.id)})

                if (index < users.lastIndex)
                    Divider(
                        modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                        color = Color.LightGray,
                        thickness = 1.dp
                    )
            }
        }
    }
}