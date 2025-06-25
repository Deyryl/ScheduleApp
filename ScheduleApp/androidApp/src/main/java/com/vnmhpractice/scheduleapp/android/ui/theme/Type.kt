package com.vnmhpractice.scheduleapp.android.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.vnmhpractice.scheduleapp.android.R

val montserratAlternates = FontFamily(
    Font(R.font.montserrat_alternates_thin, weight = FontWeight.Thin),
    Font(R.font.montserrat_alternates_light, weight = FontWeight.Light),
    Font(R.font.montserrat_alternates, weight = FontWeight.Normal),
    Font(R.font.montserrat_alternates_medium, weight = FontWeight.Medium),
    Font(R.font.montserrat_alternates_semibold, weight = FontWeight.SemiBold)
)

val typography = Typography(
    displayLarge = TextStyle(
        fontFamily = montserratAlternates,
        fontWeight = FontWeight.SemiBold,
        fontSize = 50.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = montserratAlternates,
        fontWeight = FontWeight.Medium,
        fontSize = 32.sp
    )
)