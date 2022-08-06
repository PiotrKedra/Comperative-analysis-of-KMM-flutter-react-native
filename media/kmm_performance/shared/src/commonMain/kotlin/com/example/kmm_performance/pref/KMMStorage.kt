package com.example.kmm_performance.pref

class KMMStorage(val context: SPref) {

    fun getString(key: String): String {
        return context.getString(key)
    }

    fun putString(key: String, value: String) {
        context.setString(key,value)
    }
}