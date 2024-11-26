package ru.muztache.feature.tuner.ui.composable

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.muztache.feature.tuner.ui.engine.tone.Tone

private val incorrectNoteBrush = Brush.linearGradient(listOf(Color.Gray, Color.Red))
private val correctNoteBrush = Brush.linearGradient(listOf(Color.Gray, Color.Green))

data class NoteIndicatorColors(
    val incorrectTone: Color,
    val correctTone: Color
)

val NoteIndicatorColorsDefaults = NoteIndicatorColors(
    incorrectTone = Color.Red,
    correctTone = Color.Green
)

@Composable
fun NoteIndicator(
    modifier: Modifier = Modifier,
    radius: Dp,
    note: Tone,
    isCorrect: Boolean = false,
    colors: NoteIndicatorColors = NoteIndicatorColorsDefaults
) {
    val textMeasurer = rememberTextMeasurer()
    Canvas(
        modifier = Modifier
            .requiredSize(radius * 2)
            .then(modifier)
    ) {
        val radiusPx = radius.toPx()

        val brush = if (isCorrect)
            Brush.linearGradient(listOf(Color.Gray, colors.correctTone))
        else
            Brush.linearGradient(listOf(Color.Gray, colors.incorrectTone))

        val textMeasureResult = textMeasurer.measure(
            text = note.toString(),
            style = TextStyle.Default.copy(
                fontSize = 96.sp
            )
        )
        drawCircle(
            brush = brush,
            radius = radiusPx
        )
        drawText(
            textMeasureResult,
            color = Color.White,
            topLeft = Offset(
                x = radiusPx - textMeasureResult.size.width / 2,
                y = radiusPx - textMeasureResult.size.height / 2
            )
        )
    }
}

@Preview
@Composable
private fun NoteIndicatorPreview() {
    NoteIndicator(
        radius = 128.dp,
        note = Tone.A4,
        isCorrect = true
    )
}