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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle, // we don't need it here, but hilt requires it?
    private val getUserList: GetUserList,
    private val createUser: CreateUser,
) : ViewModel() {

    val state: MutableState<UserListState> = mutableStateOf(UserListState())

    init {
        loadUsers()
    }

    private fun loadUsers() {
        getUserList.execute(
            page = state.value.page
        ).onEach { dataState ->

            println("XDXD1 ${dataState.isLoading}")

            state.value = state.value.copy(isLoading = dataState.isLoading)

            dataState.data?.let { users ->
                appendUser(users = users)
            }

            dataState.message?.let { message ->
                println(message)
            }
        }.launchIn(viewModelScope)
    }

    private fun createUser(user: User) {
        createUser.execute(user).onEach { dataState ->
            println("dupa: creating user")
            println(dataState.isLoading)

            dataState.data?.let { users ->
                println(users)
            }

            dataState.message?.let { message ->
                println(message)
            }
        }.launchIn(viewModelScope)
    }

    private fun appendUser(users: List<User>) {
        val curr = ArrayList(state.value.users)
        curr.addAll(users)
        state.value = state.value.copy(users = curr)
    }

}