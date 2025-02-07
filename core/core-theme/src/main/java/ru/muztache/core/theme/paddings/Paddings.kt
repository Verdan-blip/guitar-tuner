package ru.muztache.core.theme.paddings

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Paddings(
    val extraSmall: Dp,
    val small: Dp,
    val normal: Dp,
    val medium: Dp,
    val large: Dp,
    val extraLarge: Dp
)

val muztachePaddings = Paddings(
    extraSmall = 2.dp,
    small = 4.dp,
    normal = 8.dp,
    medium = 16.dp,
    large = 24.dp,
    extraLarge = 32.dp
)

val LocalMuztachePaddings = staticCompositionLocalOf<Paddings> {
    error("Paddings has not been provided")
}
