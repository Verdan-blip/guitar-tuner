package ru.muztache.core.common.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

val Dp.asPxValue: Float @Composable get() = with(LocalDensity.current) { toPx() }

val Float.asDpValue: Dp @Composable get() = with(LocalDensity.current) { toDp() }