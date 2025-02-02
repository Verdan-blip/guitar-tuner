package ru.muztache.core.common.compose

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.IntSize

fun layoutPositionsFromCenter(
    center: Offset,
    itemPaddingBetween: Offset,
    itemSize: Size,
    layoutMatrix: IntSize
): List<Offset> = MutableList(layoutMatrix.width * layoutMatrix.height) { index ->
        val colIndex = 1 - index / layoutMatrix.height
        val circularColumnIndex = colIndex - (layoutMatrix.width - 1) / 2f

        val rowIndex = when (index) {
            0 -> 2
            1 -> 1
            2 -> 0
            3 -> 0
            4 -> 1
            else -> 2
        }
        val circularRowIndex = rowIndex - (layoutMatrix.height - 1) / 2f

        center + Offset(
            x = -itemSize.width / 2f + (itemPaddingBetween.x + itemSize.width) * circularColumnIndex,
            y = -itemSize.height / 2f + (itemPaddingBetween.y + itemSize.height) * circularRowIndex
        )
    }