package com.example.kmm_network.interactors

import com.example.kmm_network.datasource.cache.UserCache
import com.example.kmm_network.datasource.network.UserService
import com.example.kmm_network.domain.DataState
import com.example.kmm_network.domain.model.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetUserList(
    private val userService: UserService,
    private val userCache: UserCache
) {

    fun execute(page: Int) : Flow<DataState<List<User>>> = flow {

        emit(DataState.loading())

        try {
            val users = userService.getUsers(page)

            delay(500)

            userCache.insert(users)

            emit(DataState.success(data = userCache.getAll(page)))
        } catch (e: Exception) {
            emit(DataState.error(message = e.message ?: "Unknown error"))
        }
    }
}