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
import com.example.kmm_performance.pref.KMMStorage
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val service = UserServiceImpl(KtorClientFactory().build())

        val kmmStorage = KMMStorage(this)

        setContent {
            MaterialTheme {
                MainView(service = service, pref = kmmStorage)
            }
        }
    }
}

@Composable
fun MainView(
    service: UserServiceImpl,
    pref: KMMStorage
) {

    val coroutineScope = rememberCoroutineScope()

    val (list, setList) = remember { mutableStateOf<List<User>?>(null) }
    val (s, setS) = remember { mutableStateOf<String>("") }
    val (fetchTime, setFetchTime) = remember { mutableStateOf<Long>(0) }
    val (saveTime, setSaveTime) = remember { mutableStateOf<Long>(0) }
    val (readTime, setReadTime) = remember { mutableStateOf<Long>(0) }

    val fetch: () -> Unit = {
        coroutineScope.launch {
            val elapsed = measureTimeMillis {
                val tmp = service.getUsers()
                setList(tmp)
            }
            setFetchTime(elapsed)
        }
    }

    val saveData: () -> Unit = {
        val elapsed = measureTimeMillis {
            pref.putString("json", list.toString())
        }
        setSaveTime(elapsed)
    }

    val readData: () -> Unit = {
        val elapsed = measureTimeMillis {
            val string = pref.getString("json")
            setS(string)
        }
        setReadTime(elapsed)
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
        Text(text = "$saveTime miliseconds")
        Button(onClick = { saveData() }) {
            Text(text = "Measure save")
        }
        Text(text = "$s")
        Text(text = "$readTime miliseconds")
        Button(onClick = { readData() }) {
            Text(text = "Measure read")
        }
    }
}
