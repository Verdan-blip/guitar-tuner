package ru.muztache.core.common.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize

val Dp.asPxValue: Float @Composable get() = with(LocalDensity.current) {
    toPx()
}

val Float.asDpValue: Dp @Composable get() = with(LocalDensity.current) {
    toDp()
}

val DpSize.asSize: Size @Composable get() = with(LocalDensity.current) {
    toSize()
}

val DpSize.asOffset: Offset @Composable get() = with(LocalDensity.current) {
    Offset(width.toPx(), height.toPx())
}
