package com.example.kmm_performance.pref

import platform.Foundation.NSUserDefaults
import platform.darwin.NSObject

actual typealias SPref = NSObject

actual fun SPref.getString(key: String) : String {
    return NSUserDefaults.standardUserDefaults.stringForKey(key).toString()
}

actual fun SPref.setString(key: String, value : String){
    NSUserDefaults.standardUserDefaults.setString(value,key)
}