package com.example.kmm_performance.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.kmm_performance.KtorClientFactory
import com.example.kmm_performance.User
import com.example.kmm_performance.UserServiceImpl
import kotlinx.coroutines.launch
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val service = UserServiceImpl(KtorClientFactory().build())

        setContent {
            MaterialTheme {
                MainView(service = service)
            }
        }
    }
}

@Composable
fun MainView(
    service: UserServiceImpl
) {

    val coroutineScope = rememberCoroutineScope()

    val (list, setList) = remember { mutableStateOf<List<User>?>(null) }
    val (fetchTime, setFetchTime) = remember { mutableStateOf<Long>(0) }

    val fetch: () -> Unit = {
        coroutineScope.launch {
            val elapsed = measureTimeMillis {
                val tmp = service.getUsers()
                setList(tmp)
            }
            setFetchTime(elapsed)
        }
    }

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "$fetchTime miliseconds")
        Button(onClick = { fetch() }) {
            Text(text = "Measure fetch api")
        }
    }
}
