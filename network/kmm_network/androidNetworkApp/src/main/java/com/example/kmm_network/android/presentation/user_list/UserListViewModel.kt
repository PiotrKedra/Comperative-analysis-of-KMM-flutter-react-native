package com.example.kmm_network.android.presentation.user_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kmm_network.presentation.user_list.UserListState
import com.example.kmm_network.domain.model.User
import com.example.kmm_network.interactors.CreateUser
import com.example.kmm_network.interactors.GetUserList
import com.example.kmm_network.presentation.user_list.UserListEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle, // we don't need it here, but hilt requires it?
    private val getUserList: GetUserList,
) : ViewModel() {

    val state: MutableState<UserListState> = mutableStateOf(UserListState())

    init {
        onTriggerEvent(UserListEvents.LoadUsers)
    }

    fun onTriggerEvent(event: UserListEvents) {
        when (event) {
            UserListEvents.LoadUsers -> {
                loadUsers()
            }
            UserListEvents.NextPage -> {
                nextPage()
            }
            UserListEvents.Refresh -> {
                refresh()
            }
            else -> {
                println("### some event")
            }
        }
    }

    private fun refresh() {
        state.value = state.value.copy(page = 1)

        getUserList.executeJustCache(
            page = state.value.page
        ).onEach { dataState ->

            state.value = state.value.copy(isLoading = dataState.isLoading)

            dataState.data?.let { users ->
                state.value = state.value.copy(users = users)
            }

            dataState.message?.let { message ->
                println(message)
            }
        }.launchIn(viewModelScope)
    }

    private fun loadUsers() {
        getUserList.execute(
            page = state.value.page
        ).onEach { dataState ->

            state.value = state.value.copy(isLoading = dataState.isLoading)

            dataState.data?.let { users ->
                appendUser(users = users)
            }

            dataState.message?.let { message ->
                println(message)
            }
        }.launchIn(viewModelScope)
    }

    private fun nextPage() {
        state.value = state.value.copy(page = state.value.page + 1)
        loadUsers()
    }

    private fun appendUser(users: List<User>) {
        val curr = ArrayList(state.value.users)
        curr.addAll(users)
        state.value = state.value.copy(users = curr)
    }

}