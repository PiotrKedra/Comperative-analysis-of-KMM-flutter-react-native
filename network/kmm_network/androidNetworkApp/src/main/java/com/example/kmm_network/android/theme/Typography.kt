package com.example.kmm_network.android.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.kmm_network.android.R

private val JetBrainsMono = FontFamily(
    Font(R.font.jetbrainsmono_regular, FontWeight.W400),
    Font(R.font.jetbrainsmono_medium, FontWeight.W500),
    Font(R.font.jetbrainsmono_semibold, FontWeight.W600),
)

val JetBrainsMonoTypography = Typography(
    h1 = TextStyle(
        fontFamily = JetBrainsMono,
        fontWeight = FontWeight.W600,
        fontSize = 30.sp
    ),
    h3 = TextStyle(
        fontFamily = JetBrainsMono,
        fontWeight = FontWeight.W600,
        fontSize = 22.sp
    ),
    h4 = TextStyle(
        fontFamily = JetBrainsMono,
        fontWeight = FontWeight.W400,
        fontSize = 20.sp
    ),
    h5 = TextStyle(
        fontFamily = JetBrainsMono,
        fontWeight = FontWeight.W400,
        fontSize = 12.sp,
        color = Gray
    ),
    body1 = TextStyle(
        fontFamily = JetBrainsMono,
        fontWeight = FontWeight.W500,
        fontSize = 20.sp
    ),
    body2 = TextStyle(
        fontFamily = JetBrainsMono,
        fontWeight = FontWeight.W500,
        fontSize = 12.sp
    ),
    button = TextStyle(
        fontFamily = JetBrainsMono,
        fontWeight = FontWeight.W500,
        fontSize = 20.sp
    ),
)