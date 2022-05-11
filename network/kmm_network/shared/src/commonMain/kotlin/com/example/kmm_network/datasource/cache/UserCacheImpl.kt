package com.example.kmm_network.datasource.cache

import com.example.kmm_network.datasource.network.UserServiceImpl.Companion.PAGINATION_PAGE_SIZE
import com.example.kmm_network.datasource.toUser
import com.example.kmm_network.datasource.fromDBtoUserList
import com.example.kmm_network.domain.model.User
import com.example.kmmnetwork.datasource.cache.UserDBQueries

class UserCacheImpl(
    private val userDatabase: UserDatabase,
): UserCache {

    private val queries: UserDBQueries = userDatabase.userDBQueries

    override fun insert(user: User) {
        queries.insertUser(
            id = user.id.toLong(),
            email = user.email,
            first_name = user.firstName,
            last_name = user.lastName,
            avatar = user.avatar
        )
    }

    override fun insert(userList: List<User>) {
        for (user in userList){
            insert(user)
        }
    }

    override fun get(id: Int): User? {
        return try {
            queries.getUserById(id=id.toLong())
                .executeAsOne()
                .toUser()
        } catch (e: NullPointerException) {
            null
        }
    }

    override fun getAll(page: Int): List<User> {
        return queries.getAllUsers(
                pageSize = PAGINATION_PAGE_SIZE.toLong(),
                offset = ((page - 1) * PAGINATION_PAGE_SIZE).toLong()
        ).executeAsList().fromDBtoUserList()
    }

    override fun update(user: User) {
        queries.updateUser(
            id = user.id.toLong(),
            email = user.email,
            firstName = user.firstName,
            lastName = user.lastName,
            avatar = user.avatar
        )
    }

    override fun delete(id: Int) {
        queries.deleteUser(id.toLong())
    }
}