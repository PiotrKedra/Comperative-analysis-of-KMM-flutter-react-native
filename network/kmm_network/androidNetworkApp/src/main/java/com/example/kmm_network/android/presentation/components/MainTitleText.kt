package com.example.kmm_network.android.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kmm_network.android.theme.Primary

@Composable
fun MainTitleText(
    text: String
) {
    Box (
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, top = 60.dp, bottom = 15.dp)
    ){
        Box(
            modifier = Modifier
                .width(80.dp)
                .height(48.dp)
                .background(Primary)
                .align(Alignment.BottomEnd)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.h1,
        )
    }
}