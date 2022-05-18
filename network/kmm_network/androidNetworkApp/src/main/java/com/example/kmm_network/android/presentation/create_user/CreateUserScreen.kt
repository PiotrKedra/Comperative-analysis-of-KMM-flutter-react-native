package com.example.kmm_network.android.presentation.create_user

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.kmm_network.android.presentation.components.MainButton
import com.example.kmm_network.android.presentation.components.MainTitleText
import com.example.kmm_network.android.theme.AppTheme

@Composable
fun CreateUserScreen() {

    var emailInput by remember { mutableStateOf(TextFieldValue("")) }
    var nameInput by remember { mutableStateOf(TextFieldValue("")) }
    var lastNameInput by remember { mutableStateOf(TextFieldValue("")) }


    AppTheme(displayProgressBar = false) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            MainTitleText(text = "New user")
            Column (
                modifier = Modifier.padding(20.dp)
            ){
                OutlinedTextField(
                    value = emailInput,
                    onValueChange = { emailInput = it },
                    label = { Text("Email") }
                )
                OutlinedTextField(
                    value = nameInput,
                    onValueChange = { nameInput = it },
                    label = { Text("First Name") }
                )
                OutlinedTextField(
                    value = lastNameInput,
                    onValueChange = { lastNameInput = it },
                    label = { Text("Last Name") }
                )
                Spacer(modifier = Modifier.padding(10.dp))
                MainButton(text = "Create new user", onClick = {})
            }
        }
    }
}

