package com.example.kmm_network.android.presentation.modify_user

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
import com.example.kmm_network.domain.model.User
import com.example.kmm_network.presentation.BasicState
import kotlin.random.Random

const val BASIC_AVATAR_URL = "https://images.unsplash.com/photo-1494790108377-be9c29b29330?crop=entropy&cs=tinysrgb&fm=jpg&ixlib=rb-1.2.1&q=80&raw_url=true&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687"

@Composable
fun ModifyUserScreen(
    state: BasicState,
    modifyUser: (User) -> Unit,
    goBack: () -> Unit,
    isCreate: Boolean = true,
) {

    var emailInput by remember {
        mutableStateOf(TextFieldValue(if (isCreate) "" else state.user!!.email))
    }
    var nameInput by remember {
        mutableStateOf(TextFieldValue(if (isCreate) "" else state.user!!.firstName))
    }
    var lastNameInput by remember {
        mutableStateOf(TextFieldValue(if (isCreate) "" else state.user!!.lastName))
    }

    AppTheme(displayProgressBar = state.isLoading) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            if (isCreate)
                MainTitleText(text = "New user")
            else
                MainTitleText(text = "Update user")
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
                MainButton(text = "Submit", onClick = {
                    val user = User(
                        id = if (isCreate) Random.nextInt(0, 9999) else state.user!!.id,
                        email = emailInput.text,
                        firstName = nameInput.text,
                        lastName = lastNameInput.text,
                        avatar = if (isCreate) BASIC_AVATAR_URL else state.user!!.avatar
                    )
                    modifyUser(user)
                    goBack()
                })
            }
        }
    }
}

