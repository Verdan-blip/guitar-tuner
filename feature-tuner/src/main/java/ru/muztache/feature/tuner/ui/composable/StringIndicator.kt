package ru.muztache.feature.tuner.ui.composable

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.muztache.feature.tuner.ui.engine.tone.Tone

private val tailWidth: Dp = 4.dp

data class StringIndicatorColors(
    val correctTone: Color,
    val incorrectTone: Color,
    val selectedTextColor: Color,
    val unselectedTextColor: Color
)

val StringIndicatorColorsDefault = StringIndicatorColors(
    correctTone = Color.Green,
    incorrectTone = Color.Red,
    selectedTextColor = Color.White,
    unselectedTextColor = Color.Black
)

@Composable
fun StringIndicator(
    modifier: Modifier = Modifier,
    radius: Dp,
    tailLength: Dp = 32.dp,
    note: Tone,
    isCorrect: Boolean = false,
    isSelected: Boolean = false,
    colors: StringIndicatorColors = StringIndicatorColorsDefault,
    onClick: () -> Unit
) {
    val textMeasurer = rememberTextMeasurer()

    Canvas(
        modifier = Modifier
            .requiredSize(
                width = radius * 2,
                height = radius * 2 + tailLength
            )
            .clickable { onClick() }
            .then(modifier)
    ) {
        val radiusPx = radius.toPx()
        val tailWidthPx = tailWidth.toPx()

        val toneColor = if (isCorrect)
            colors.correctTone
        else
            colors.incorrectTone

        val textColor = if (isSelected)
            colors.selectedTextColor
        else
            colors.unselectedTextColor

        val brush = Brush.linearGradient(
            colors = listOf(Color.Gray, toneColor)
        )

        val textMeasureResult = textMeasurer.measure(
            text = note.toString(),
            style = TextStyle.Default.copy(
                fontSize = 14.sp
            )
        )

        if (isSelected) {
            drawSelectedStringIndicator(
                brush = brush,
                radiusPx = radiusPx,
                tailWidthPx = tailWidthPx,
                tailLengthPx = tailLength.toPx()
            )
        }

        drawText(
            textMeasureResult,
            color = textColor,
            topLeft = Offset(
                x = radiusPx - textMeasureResult.size.width / 2,
                y = radiusPx - textMeasureResult.size.height / 2
            )
        )
    }
}

fun DrawScope.drawSelectedStringIndicator(
    brush: Brush,
    radiusPx: Float,
    tailWidthPx: Float,
    tailLengthPx: Float
) {
    val path = Path().apply {
        arcTo(
            rect = Rect(
                offset = Offset(
                    x = 0f,
                    y = 0f
                ),
                size = Size(
                    width = radiusPx * 2,
                    height = radiusPx * 2
                )
            ),
            startAngleDegrees = 180f,
            sweepAngleDegrees = 180f,
            forceMoveTo = true
        )
        arcTo(
            rect = Rect(
                offset = Offset(
                    x = 0f,
                    y = 0f
                ),
                size = Size(
                    width = radiusPx * 2,
                    height = radiusPx * 2
                )
            ),
            startAngleDegrees = 0f,
            sweepAngleDegrees = 180f,
            forceMoveTo = false
        )
        moveTo(
            x = radiusPx + tailWidthPx / 2f,
            y = radiusPx * 2
        )
        lineTo(
            x = radiusPx + tailWidthPx / 2f,
            y = radiusPx * 2 + tailLengthPx
        )
        lineTo(
            x = radiusPx - tailWidthPx / 2f,
            y = radiusPx * 2 + tailLengthPx
        )
        lineTo(
            x = radiusPx - tailWidthPx / 2f,
            y = radiusPx * 2
        )
    }
    drawPath(
        path = path,
        brush = brush
    )
}

@Preview
@Composable
private fun StringIndicatorPreview() {
    StringIndicator(
        note = Tone.A4,
        radius = 16.dp,
        onClick = { }
    )
}