package com.example.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcompose.data.FearAndGreedIndex
import com.example.jetpackcompose.ui.theme.Black
import com.example.jetpackcompose.ui.theme.JetpackcomposeTheme
import com.example.jetpackcompose.ui.theme.Light
import com.example.jetpackcompose.ui.theme.White
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            JetpackcomposeTheme {
                HelloWorldCryptoNav()
            }
        }
    }
}

@Composable
fun HelloWorldCryptoNav() {

    val homeViewModel = viewModel(modelClass = HomeViewModel::class.java)
    val state by homeViewModel.state.collectAsState()

    val navController = rememberNavController()
    NavHost(navController, startDestination = "main") {
        composable(route = "main") {
            HelloWorldCrypto(navController, state)
        }
        composable(route = "helloWorld") {
            HelloWorldScreen()
        }
    }
}

@Composable
fun HelloWorldCrypto(navController: NavController, state: FearAndGreedIndex) {

    Surface(
        color = Black,
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 200.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text="Fear and Greed Index",
                color=White,
                style = MaterialTheme.typography.h4,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Monospace,
            )
            OutlinedButton(onClick = { navController.navigate("helloWorld") },
                modifier= Modifier.size(160.dp),  //avoid the oval shape
                shape = CircleShape,
                border= BorderStroke(1.dp, Color.White),
                contentPadding = PaddingValues(0.dp),  //avoid the little icon
                colors = ButtonDefaults.outlinedButtonColors(backgroundColor =  Color.Transparent),
            ) {
                Text(
                    text=state.data[0].value,
                    color=White,
                    style = MaterialTheme.typography.h1,
                    fontFamily = FontFamily.Monospace,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }

            Column() {
                AdditionalData("Value", state.data[0].value)
                AdditionalData("Classification", state.data[0].value_classification)
                AdditionalData("Timestamp", state.data[0].timestamp)
                AdditionalData("Next update", state.data[0].time_until_update)
            }
        }
    }
}

@Composable
fun AdditionalData(key: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(text=key, color=Light)
        Text(text=value, color=White)
    }
}


@Composable
fun HelloWorldScreen() {
    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Hello World!")
        }
    }
}


