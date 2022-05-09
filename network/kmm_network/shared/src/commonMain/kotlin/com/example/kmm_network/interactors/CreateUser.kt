package com.example.kmm_network.interactors

import com.example.kmm_network.datasource.network.UserService
import com.example.kmm_network.domain.DataState
import com.example.kmm_network.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CreateUser(
    private val userService: UserService
) {

    fun execute(user: User) : Flow<DataState<User>> = flow {

        emit(DataState.loading())

        try {
            val createdUser = userService.create(user)
            emit(DataState.success(data = createdUser))
        } catch (e: Exception) {
            emit(DataState.error(message = e.message ?: "Unknown error"))
        }
    }
}