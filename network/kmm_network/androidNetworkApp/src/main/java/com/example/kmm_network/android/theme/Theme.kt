package com.example.kmm_network.android.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

private val LightThemeColors = lightColors(
    primary = Primary,
    onPrimary = Black,
    secondary = Gray,
    onSecondary = LightGray,
    background = White,
    onBackground = Black,
)

@Composable
fun AppTheme(
    displayProgressBar: Boolean,
    content: @Composable () -> Unit
) {
    MaterialTheme (
        colors = LightThemeColors,
        typography = JetBrainsMonoTypography,
        shapes = AppShapes
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = White)
        ) {
            content()
            if (displayProgressBar) {
                // TODO
            }
        }
    }
}