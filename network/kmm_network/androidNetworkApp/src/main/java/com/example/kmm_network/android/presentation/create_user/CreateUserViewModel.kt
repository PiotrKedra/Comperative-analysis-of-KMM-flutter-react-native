package com.example.kmm_network.android.presentation.create_user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kmm_network.domain.model.User
import com.example.kmm_network.interactors.CreateUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CreateUserViewModel  @Inject constructor(
    private val createUser: CreateUser,
): ViewModel(){
    fun createUser(user: User) {
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
}