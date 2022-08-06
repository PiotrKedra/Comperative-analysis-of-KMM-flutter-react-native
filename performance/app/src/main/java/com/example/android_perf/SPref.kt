package com.example.android_perf

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

typealias SPref = Activity

fun SPref.getString(key: String ) : String {
    val prefs: SharedPreferences = this.getSharedPreferences("", Context.MODE_PRIVATE)
    return prefs.getString(key, "d")!!
}


fun SPref.setString(key: String, value: String) {
    val prefs: SharedPreferences = this.getSharedPreferences("", Context.MODE_PRIVATE)
    val editor = prefs.edit()
    editor.putString(key,value)
    editor.apply()
}

class KMMStorage(val context: SPref) {

    fun getString(key: String): String {
        return context.getString(key)
    }

    fun putString(key: String, value: String) {
        context.setString(key,value)
    }
}