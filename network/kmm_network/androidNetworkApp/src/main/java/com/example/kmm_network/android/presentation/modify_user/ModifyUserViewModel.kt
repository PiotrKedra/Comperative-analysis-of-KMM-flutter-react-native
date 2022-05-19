package com.example.kmm_network.android.presentation.modify_user

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kmm_network.domain.model.User
import com.example.kmm_network.interactors.CreateUser
import com.example.kmm_network.interactors.GetUser
import com.example.kmm_network.interactors.UpdateUser
import com.example.kmm_network.presentation.BasicState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ModifyUserViewModel  @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getUser: GetUser,
    private val createUser: CreateUser,
    private val updateUser: UpdateUser,
): ViewModel(){

    val state: MutableState<BasicState> = mutableStateOf(BasicState())

    init {
        // every argument that we are passing here through backStackEntry we can automatically access it here (savedStateHandle) thanks to hilt
        savedStateHandle.get<Int>("userId")?.let { userId ->
            getUser(userId)
        }
    }

    private fun getUser(userId: Int) {
        getUser.execute(userId).onEach { dataState ->
            state.value = state.value.copy(isLoading = dataState.isLoading)

            dataState.data?.let { user ->
                println(user)
                state.value = state.value.copy(user = user)
            }

            dataState.message?.let { message ->
                println(message)
            }
        }.launchIn(viewModelScope)
    }

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

    fun update(user: User) {
        updateUser.execute(user).onEach { dataState ->
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