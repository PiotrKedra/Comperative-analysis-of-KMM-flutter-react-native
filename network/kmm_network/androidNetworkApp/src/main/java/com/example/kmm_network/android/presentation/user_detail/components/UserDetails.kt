package com.example.kmm_network.android.presentation.user_detail.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kmm_network.android.presentation.components.*
import com.example.kmm_network.android.presentation.user_list.LOREM_IPSUM
import com.example.kmm_network.domain.model.User

@Composable
fun UserDetails(
    user: User
) {
    Box (
        modifier = Modifier.fillMaxSize()
    ){
        Column {
            MainTitleText(text = "${user.firstName} ${user.lastName} ")
            Column (
                modifier = Modifier.padding(20.dp)
            ){
                UserImage(url = user.avatar, contentDescription = user.email, imgSize = LARGE_IMG_SIZE)
                Text(
                    text = user.email,
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier
                        .padding(top = 25.dp)
                )
                Text(
                    text = LOREM_IPSUM,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth(0.6F)
                )

            }
        }
        Row (
            modifier = Modifier
                .padding(bottom = 20.dp, start = 20.dp)
                .align(Alignment.BottomStart),
        ) {
            SecondaryButton(text = "delete", onClick = {})
            Spacer(modifier = Modifier.padding(10.dp))
            MainButton(text = "update", onClick = {})
        }
    }
}