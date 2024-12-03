package ru.muztache.core.theme.corners

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Corners(
    val extraSmall: Dp,
    val small: Dp,
    val normal: Dp,
    val medium: Dp,
    val large: Dp,
    val extraLarge: Dp
)

val muztacheCorners = Corners(
    extraSmall = 2.dp,
    small = 4.dp,
    normal = 8.dp,
    medium = 16.dp,
    large = 24.dp,
    extraLarge = 32.dp
)

val LocalMuztacheCorners = staticCompositionLocalOf<Corners> {
    error("Corners has not been provided")
}