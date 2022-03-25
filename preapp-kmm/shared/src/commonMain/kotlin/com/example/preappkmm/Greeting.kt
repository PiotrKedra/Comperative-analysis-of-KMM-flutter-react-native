package com.example.preappkmm

class Greeting {
    fun greeting(): String {
        return "Hello bob, ${Platform().platform}!"
    }
}