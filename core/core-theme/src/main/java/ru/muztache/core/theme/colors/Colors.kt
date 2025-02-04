package ru.muztache.core.theme.colors

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class ColorScheme(
    val primary: Color,
    val onPrimary: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val surface: Color,
    val background: Color,
    val border: Color,
    val neutral: Color,
    val reference: Color
)

val muztacheLightColorScheme: ColorScheme = ColorScheme(
    primary = Color(0xFF42C83C),
    onPrimary = Color(0xFFFFFFFF),
    textPrimary = Color(0xFF383838),
    textSecondary = Color(0xFF797979),
    surface = Color(0xFFE6E6E6),
    background = Color(0xFFF2F2F2),
    border = Color(0xFF000000),
    neutral = Color(0xFFB4B4B4),
    reference = Color(0xFF288CE9)
)

val muztacheDarkColorScheme: ColorScheme = ColorScheme(
    primary = Color(0xFF42C83C),
    onPrimary = Color(0xFFFFFFFF),
    textPrimary = Color(0xFF383838),
    textSecondary = Color(0xFF797979),
    surface = Color(0xFFE6E6E6),
    background = Color(0xFFF2F2F2),
    border = Color(0xFF000000),
    neutral = Color(0xFFB4B4B4),
    reference = Color(0xFF288CE9)
)

val LocalMuztacheColors = staticCompositionLocalOf<ColorScheme> {
    error("Colors has not been provided")
}