package ru.muztache.core.common.compose

import androidx.compose.ui.geometry.Offset
import ru.muztache.core.util.math.sqr

fun onIntersect(
    target: Offset,
    points: List<Offset>,
    pointRadius: Float,
    callback: (index: Int) -> Unit
) {
    points.forEachIndexed { index, point ->
        if (sqr(target.x - point.x - pointRadius) + sqr(target.y - point.y - pointRadius) <= sqr(pointRadius)) {
            callback(index)
            return
        }
    }
}