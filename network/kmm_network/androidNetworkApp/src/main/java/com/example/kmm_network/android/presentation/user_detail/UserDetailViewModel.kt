package com.example.kmm_network.android.presentation.user_detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kmm_network.domain.model.User
import com.example.kmm_network.interactors.DeleteUser
import com.example.kmm_network.interactors.GetUser
import com.example.kmm_network.interactors.UpdateUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getUser: GetUser,
    private val deleteUser: DeleteUser,
) : ViewModel() {

    val user: MutableState<User?> = mutableStateOf(null)

    init {
        // every argument that we are passing here through backStackEntry we can automatically access it here (savedStateHandle) thanks to hilt
        savedStateHandle.get<Int>("userId")?.let { userId ->
            getUser(userId)
        }
    }

    private fun getUser(userId: Int) {
        getUser.execute(userId).onEach { dataState ->
            println(dataState.isLoading)

            dataState.data?.let { user ->
                println(user)
                this.user.value = user
            }

            dataState.message?.let { message ->
                println(message)
            }
        }.launchIn(viewModelScope)
    }

    fun deleteUser(id: Int) {
        deleteUser.execute(id).onEach { dataState ->
            println(dataState.isLoading)

            dataState.message?.let { message ->
                println(message)
            }
        }.launchIn(viewModelScope)
    }
}