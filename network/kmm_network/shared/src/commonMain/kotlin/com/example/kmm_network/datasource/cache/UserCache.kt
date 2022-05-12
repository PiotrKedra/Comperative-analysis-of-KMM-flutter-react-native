package com.example.kmm_network.datasource.cache

import com.example.kmm_network.domain.model.User

interface UserCache {
    fun insert(user: User)

    fun insert(userList: List<User>)

    fun get(id: Int): User?

    fun getAll(page: Int) : List<User>

    fun update(user: User)

    fun delete(id: Int)
}