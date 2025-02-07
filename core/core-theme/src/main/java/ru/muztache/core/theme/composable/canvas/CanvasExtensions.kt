package ru.muztache.core.theme.composable.canvas

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope

fun DrawScope.drawCross(
    center: Offset,
    size: Size,
    color: Color,
    strokeWidth: Float
) {
    val halfSize = size / 2f
    drawLine(
        color = color,
        start = Offset(center.x - halfSize.width, center.y - halfSize.height),
        end = Offset(center.x + halfSize.width, center.y + halfSize.height),
        strokeWidth = strokeWidth
    )
    drawLine(
        color = color,
        start = Offset(center.x + halfSize.width, center.y - halfSize.height),
        end = Offset(center.x - halfSize.width, center.y + halfSize.height),
        strokeWidth = strokeWidth
    )
}
