package com.example.kmm_performance.pref

import android.app.Activity
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

actual typealias SPref = Activity

actual fun SPref.getString(key: String ) : String {
    val prefs: SharedPreferences = this.getSharedPreferences("", MODE_PRIVATE)
    return prefs.getString(key, "d")!!
}


actual fun SPref.setString(key: String, value: String) {
    val prefs: SharedPreferences = this.getSharedPreferences("", MODE_PRIVATE)
    val editor = prefs.edit()
    editor.putString(key,value)
    editor.apply()
}