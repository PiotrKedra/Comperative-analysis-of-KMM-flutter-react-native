package com.example.kmm_network.datasource.cache

import com.squareup.sqldelight.db.SqlDriver

class UserDatabaseFactory(
    private val driverFactory: DriverFactory
) {
    fun createDatabase(): UserDatabase {
        return UserDatabase(driverFactory.createDriver())
    }
}

expect class DriverFactory {
    fun createDriver(): SqlDriver
}