package com.example.glowgetter.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.example.glowgetter.R


val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val jostFont = GoogleFont(name = "Jost")
val jostFontFamily = FontFamily(
    Font(googleFont = jostFont, fontProvider = provider)
)

val josefinFont = GoogleFont(name = "Josefin Sans")
val josefinFontFamily = FontFamily(
    Font(googleFont = josefinFont, fontProvider = provider)
)

val jostTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = jostFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = jostFontFamily,
        fontWeight = FontWeight.Normal,
        lineHeight = 28.sp,
        fontSize = 18.sp
    ),
    bodySmall = TextStyle(
        fontFamily = jostFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    titleLarge = TextStyle(
        fontFamily = josefinFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp
    ),
    titleMedium = TextStyle(
        fontFamily = jostFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    titleSmall = TextStyle(
        fontFamily = jostFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    labelLarge = TextStyle(
        fontFamily = jostFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    labelMedium = TextStyle(
        fontFamily = jostFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp
    ),
    labelSmall = TextStyle(
        fontFamily = jostFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 10.sp
    ),
    displayMedium = TextStyle(
        fontFamily = jostFontFamily,
        fontSize = 22.sp
    ),
    displaySmall = TextStyle(
        fontFamily = jostFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    displayLarge = TextStyle(
        fontFamily = josefinFontFamily,
        fontSize = 28.sp
    )
)
