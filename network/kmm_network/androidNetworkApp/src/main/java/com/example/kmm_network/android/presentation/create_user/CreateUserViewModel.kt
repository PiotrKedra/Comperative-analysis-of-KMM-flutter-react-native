package com.example.kmm_network.android.presentation.create_user

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kmm_network.domain.model.User
import com.example.kmm_network.interactors.CreateUser
import com.example.kmm_network.presentation.BasicState
import com.example.kmm_network.presentation.user_list.UserListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CreateUserViewModel  @Inject constructor(
    private val createUser: CreateUser,
): ViewModel(){

    val state: MutableState<BasicState> = mutableStateOf(BasicState())

    fun createUser(user: User) {
        createUser.execute(user).onEach { dataState ->
            state.value = state.value.copy(isLoading = dataState.isLoading)

            dataState.data?.let { user ->
                println(user)
            }

            dataState.message?.let { message ->
                println(message)
            }
        }.launchIn(viewModelScope)
    }
}