package com.phisit.composenetfilm.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val PTSandsTypography = Typography(
    labelSmall = TextStyle(
        fontFamily = ptSandsFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = ptSandsFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
    ),
    labelLarge = TextStyle(
        fontFamily = ptSandsFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = ptSandsFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = ptSandsFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = ptSandsFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
    ),
    headlineSmall = TextStyle(
        fontFamily = ptSandsFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
    ),
    headlineMedium =  TextStyle(
        fontFamily = ptSandsFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
    ),
    headlineLarge =  TextStyle(
        fontFamily = ptSandsFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = ptSandsFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = ptSandsFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
    ),
    titleLarge = TextStyle(
        fontFamily = ptSandsFamily,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    )
)