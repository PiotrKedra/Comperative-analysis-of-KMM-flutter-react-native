package com.example.kmm_network.android.presentation.user_list

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kmm_network.android.presentation.components.MainButton
import com.example.kmm_network.android.presentation.user_list.components.UserList
import com.example.kmm_network.android.theme.AppTheme
import com.example.kmm_network.presentation.user_list.UserListEvents
import com.example.kmm_network.presentation.user_list.UserListState

@Composable
fun UserListScreen(
    state: UserListState,
    onTriggerEvent: (UserListEvents) -> Unit,
    onSelectedUser: (Int) -> Unit,
    onClickAddNewUser: () -> Unit,
    refresh: () -> Unit,
    shouldRefresh: Boolean,
) {

    println("REFRESH: $shouldRefresh")
    if (shouldRefresh) {
        refresh()
    }

    AppTheme(displayProgressBar = state.isLoading) {
        BackHandler(enabled = true) {
            onTriggerEvent(UserListEvents.NextPage)
            println("handling back")
        }
        Box (
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                UserList(
                    isLoading = state.isLoading,
                    users = state.users,
                    page = state.page,
                    onTriggerNextPage = { onTriggerEvent(UserListEvents.NextPage) },
                    onSelectedUser = onSelectedUser
                )
            }
            Row (
                modifier = Modifier
                    .padding(bottom = 20.dp, end = 20.dp)
                    .align(Alignment.BottomEnd),
            ) {
                MainButton(text = "add", onClick = {onClickAddNewUser()}, horizontalPadding = 16)
            }
        }

    }
}