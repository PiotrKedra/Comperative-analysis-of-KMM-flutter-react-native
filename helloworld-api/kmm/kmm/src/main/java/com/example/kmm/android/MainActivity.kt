package com.example.kmm.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kmm.Greeting
import android.widget.TextView
import com.example.kmm.FearAndGreedIndexApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


private lateinit var api: FearAndGreedIndexApi

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        api = FearAndGreedIndexApi()



        val tv: TextView = findViewById(R.id.text_view)
        tv.text = greet()
    }
}
