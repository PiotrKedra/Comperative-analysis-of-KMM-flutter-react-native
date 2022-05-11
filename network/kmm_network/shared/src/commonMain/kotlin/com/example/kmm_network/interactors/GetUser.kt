package com.example.kmm_network.interactors

import com.example.kmm_network.datasource.cache.UserCache
import com.example.kmm_network.datasource.network.UserService
import com.example.kmm_network.domain.DataState
import com.example.kmm_network.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetUser(
    private val userCache: UserCache
) {

    fun execute(id: Int) : Flow<DataState<User>> = flow {

        emit(DataState.loading())

        try {
            val users = userCache.get(id)
            emit(DataState.success(data = users))
        } catch (e: Exception) {
            emit(DataState.error(message = e.message ?: "Unknown error"))
        }
    }
}