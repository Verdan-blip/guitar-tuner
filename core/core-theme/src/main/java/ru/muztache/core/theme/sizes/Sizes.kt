package ru.muztache.core.theme.sizes

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Sizes(
    val extraSmall: Dp,
    val small: Dp,
    val normal: Dp,
    val medium: Dp,
    val large: Dp,
    val extraLarge: Dp
)

val muztacheSizes = Sizes(
    extraSmall = 24.dp,
    small = 32.dp,
    normal = 48.dp,
    medium = 64.dp,
    large = 96.dp,
    extraLarge = 144.dp
)

val LocalMuztacheSizes = staticCompositionLocalOf<Sizes> {
    error("Sizes has not been provided")
}
