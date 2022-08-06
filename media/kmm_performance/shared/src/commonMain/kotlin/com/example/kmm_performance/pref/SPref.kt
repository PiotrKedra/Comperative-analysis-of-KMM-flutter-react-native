package com.example.kmm_performance.pref

expect class SPref

expect fun SPref.getString(key: String) : String
expect fun SPref.setString(key: String, value: String)