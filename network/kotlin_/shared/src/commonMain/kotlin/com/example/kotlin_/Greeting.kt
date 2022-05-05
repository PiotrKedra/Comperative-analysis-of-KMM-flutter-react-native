package com.example.kotlin_

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}