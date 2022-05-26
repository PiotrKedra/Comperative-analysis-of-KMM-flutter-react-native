package com.example.kmm_network.di

import com.example.kmm_network.datasource.cache.*

class CacheModule {

    private val driverFactory: DriverFactory by lazy {DriverFactory()}
    private val userDatabase: UserDatabase by lazy {
        UserDatabaseFactory(driverFactory = driverFactory).createDatabase()
    }

    val userCache: UserCache by lazy {
        UserCacheImpl(userDatabase = userDatabase)
    }
}