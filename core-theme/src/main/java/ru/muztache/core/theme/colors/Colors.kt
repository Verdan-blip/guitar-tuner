package ru.muztache.core.theme.colors

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class ColorScheme(
    val primary: Color,
    val onPrimary: Color,
    val secondary: Color,
    val onSecondary: Color,
    val surface: Color,
    val onSurface: Color,
    val error: Color,
    val onError: Color,
    val success: Color,
    val onSuccess: Color
)

val muztacheLightColorScheme: ColorScheme = ColorScheme(
    primary = Color(171, 104, 58),
    onPrimary = Color(255, 255, 255, 255),
    secondary = Color(130, 70, 23, 255),
    onSecondary = Color(255, 255, 255, 255),
    surface = Color(213, 213, 213, 255),
    onSurface = Color(0, 0, 0, 255),
    error = Color(255, 88, 88, 255),
    onError = Color(255, 255, 255, 255),
    success = Color(85, 163, 87, 255),
    onSuccess = Color(255, 255, 255, 255)
)

val muztacheDarkColorScheme: ColorScheme = ColorScheme(
    primary = Color(171, 104, 58),
    onPrimary = Color(255, 255, 255, 255),
    secondary = Color(130, 70, 23, 255),
    onSecondary = Color(255, 255, 255, 255),
    surface = Color(43, 38, 38, 255),
    onSurface = Color(255, 255, 255, 255),
    error = Color(255, 88, 88, 255),
    onError = Color(255, 255, 255, 255),
    success = Color(85, 163, 87, 255),
    onSuccess = Color(255, 255, 255, 255)
)

val LocalMuztacheColors = staticCompositionLocalOf<ColorScheme> {
    error("Colors has not been provided")
}