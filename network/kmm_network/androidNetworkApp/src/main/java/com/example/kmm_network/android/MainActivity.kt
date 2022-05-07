package com.example.kmm_network.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import com.example.kmm_network.android.presentation.navigation.Navigation
import com.example.kmm_network.datasource.network.KtorClientFactory
import com.example.kmm_network.datasource.network.UserServiceImpl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

const val BASE_URL = "https://reqres.in/api/"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val ktorClient = KtorClientFactory().build()

        CoroutineScope(IO).launch {

            val service = UserServiceImpl(
                ktorClient,
                BASE_URL
            )
            val result = service.getUsers(1)
            println("XD")
            println(result)
        }

        setContent {
            Navigation()
        }
    }
}
