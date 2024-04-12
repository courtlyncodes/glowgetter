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

val fontName = GoogleFont("Lobster Two")

val fontFamily = FontFamily(
    Font(googleFont = fontName, fontProvider = provider)
)

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