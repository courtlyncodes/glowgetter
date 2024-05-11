package com.example.glowgetter.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.example.glowgetter.R

//Properties:
//displayLarge - displayLarge is the largest display text.
//displayMedium - displayMedium is the second largest display text.
//displaySmall - displaySmall is the smallest display text.
//headlineLarge - headlineLarge is the largest headline, reserved for short, important text or numerals. For headlines, you can choose an expressive font, such as a display, handwritten, or script style. These unconventional font designs have details and intricacy that help attract the eye.
//headlineMedium - headlineMedium is the second largest headline, reserved for short, important text or numerals. For headlines, you can choose an expressive font, such as a display, handwritten, or script style. These unconventional font designs have details and intricacy that help attract the eye.
//headlineSmall - headlineSmall is the smallest headline, reserved for short, important text or numerals. For headlines, you can choose an expressive font, such as a display, handwritten, or script style. These unconventional font designs have details and intricacy that help attract the eye.
//titleLarge - titleLarge is the largest title, and is typically reserved for medium-emphasis text that is shorter in length. Serif or sans serif typefaces work well for subtitles.
//titleMedium - titleMedium is the second largest title, and is typically reserved for medium-emphasis text that is shorter in length. Serif or sans serif typefaces work well for subtitles.
//titleSmall - titleSmall is the smallest title, and is typically reserved for medium-emphasis text that is shorter in length. Serif or sans serif typefaces work well for subtitles.
//bodyLarge - bodyLarge is the largest body, and is typically used for long-form writing as it works well for small text sizes. For longer sections of text, a serif or sans serif typeface is recommended.
//bodyMedium - bodyMedium is the second largest body, and is typically used for long-form writing as it works well for small text sizes. For longer sections of text, a serif or sans serif typeface is recommended.
//bodySmall - bodySmall is the smallest body, and is typically used for long-form writing as it works well for small text sizes. For longer sections of text, a serif or sans serif typeface is recommended.
//labelLarge - labelLarge text is a call to action used in different types of buttons (such as text, outlined and contained buttons) and in tabs, dialogs, and cards. Button text is typically sans serif, using all caps text.
//labelMedium - labelMedium is one of the smallest font sizes. It is used sparingly to annotate imagery or to introduce a headline.
//labelSmall - labelSmall is one of the smallest font sizes. It is used sparingly to annotate imagery or to introduce a headline.
// Set of Material typography styles to start with

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
    displayMedium =  TextStyle(
        fontFamily = jostFontFamily,
        fontSize = 22.sp
),
    displaySmall =  TextStyle(
        fontFamily = jostFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
),
    displayLarge =  TextStyle(
        fontFamily = josefinFontFamily,
        fontSize = 28.sp
)
)
