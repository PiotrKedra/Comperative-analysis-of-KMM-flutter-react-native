package com.example.kmm_network.android.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.kmm_network.android.theme.Black

@Composable
fun SecondaryButton(
    text: String,
    onClick: () -> Unit
) {
    TextButton(
        onClick = { onClick() },
        contentPadding = PaddingValues(
            start = 30.dp,
            top = 16.dp,
            end = 30.dp,
            bottom = 16.dp
        ),
        shape = MaterialTheme.shapes.small,
        border = BorderStroke(1.dp, Black),
        elevation = ButtonDefaults.elevation(0.dp)
    ) {
        Text(
            text = text,
            color = Black
        )
    }
}