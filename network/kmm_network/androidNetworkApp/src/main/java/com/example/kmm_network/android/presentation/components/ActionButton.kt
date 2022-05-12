package com.example.kmm_network.android.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun MainButton(
    text: String,
    onClick: () -> Unit,
    horizontalPadding: Int = 30
) {
    Button(
        onClick = { onClick() },
        contentPadding = PaddingValues(
            start = horizontalPadding.dp,
            top = 16.dp,
            end = horizontalPadding.dp,
            bottom = 16.dp
        ),
        elevation = ButtonDefaults.elevation(0.dp)
    ) {
        Text(
            text = text
        )
    }
}