package ru.muztache.feature.chords.impl.ui.composable

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.muztache.core.theme.composable.canvas.drawCross
import ru.muztache.feature.chords.impl.ui.entity.ChordModel
import ru.muztache.feature.chords.impl.ui.util.calculateBarreValue

private const val STRING_COUNT = 6
private const val FRET_COUNT = 4
private const val MAX_FRET_DIST = 4

private val size = DpSize(128.dp, 144.dp)
private val leftSpaceSize = DpSize(32.dp, 0.dp)
private val topSpaceSize = DpSize(0.dp, 20.dp)
private val bottomSpaceSize = DpSize(0.dp, 20.dp)
private val signSize = DpSize(18.dp, 18.dp)
private val fontSize = 12.sp
private val barreFontSize = 16.sp
private val strokeWidth: Dp = 1.dp
private val barreStrokeWidth: Dp = 4.dp

@Composable
fun FretBoard(
    chord: ChordModel,
    modifier: Modifier = Modifier
) {
    if (chord.frets.size != 6) {
        error("Incorrect frets size")
    }
    val textMeasurer = rememberTextMeasurer()
    Canvas(
        modifier = Modifier
            .requiredSize(size + leftSpaceSize + bottomSpaceSize)
            .then(modifier)
    ) {
        val leftOffsetPx = leftSpaceSize.width.toPx()
        val topOffsetPx = topSpaceSize.height.toPx()
        val bottomOffsetPx = bottomSpaceSize.height.toPx()

        val lineOffsetY = (size.height - topOffsetPx - bottomOffsetPx) / (STRING_COUNT - 1)
        val lineOffsetX = (size.width - leftOffsetPx) / FRET_COUNT

        val strokeWidthPx = strokeWidth.toPx()
        val barreStrokeWidthPx = barreStrokeWidth.toPx()
        val signSizePx = signSize.toSize()

        val barre = calculateBarreValue(chord, MAX_FRET_DIST)
        val frets = mutableListOf<Pair<Int, Int>>()

        repeat(FRET_COUNT + 1) { index ->
            drawLine(
                color = Color.Black,
                start = Offset(
                    x = leftOffsetPx + lineOffsetX * index,
                    y = topOffsetPx
                ),
                end = Offset(
                    x = leftOffsetPx + lineOffsetX * index,
                    y = size.height - bottomOffsetPx
                ),
                strokeWidth = if (index == 0) {
                    barreStrokeWidthPx
                } else {
                    strokeWidthPx
                }
            )
        }
        repeat(STRING_COUNT) { index ->
            val signCenter = Offset(
                x = leftOffsetPx / 2f,
                y = topOffsetPx + lineOffsetY * index
            )
            when (val sign = chord.frets[index]) {
                "X" -> {
                    drawCross(
                        center = signCenter,
                        size = signSizePx,
                        color = Color.Black,
                        strokeWidth = strokeWidthPx
                    )
                }
                "O" -> {
                    drawCircle(
                        color = Color.Black,
                        style = Stroke(strokeWidthPx),
                        radius = signSizePx.width / 2f,
                        center = signCenter
                    )
                }
                else -> {
                    frets.add(index to sign.toInt() - barre)
                }
            }
            drawLine(
                color = Color.Black,
                start = Offset(
                    x = leftOffsetPx,
                    y = topOffsetPx + lineOffsetY * index
                ),
                end = Offset(
                    x = size.width,
                    y = topOffsetPx + lineOffsetY * index
                ),
                strokeWidth = strokeWidthPx
            )
        }
        frets.forEach { (index, sign) ->
            val measureResult = textMeasurer.measure(
                text = sign.toString(),
                style = TextStyle(fontSize = fontSize)
            )
            val signPos = Offset(
                x = leftOffsetPx + lineOffsetX / 2f + lineOffsetX * (sign - 1),
                y = topOffsetPx + lineOffsetY * index
            )
            drawCircle(
                color = Color.Black,
                radius = signSizePx.width / 2f,
                center = signPos
            )
            drawText(
                textLayoutResult = measureResult,
                color = Color.White,
                topLeft = Offset(
                    x = signPos.x - measureResult.size.width / 2f,
                    y = signPos.y - measureResult.size.height / 2f
                )
            )
        }
        if (barre != 0) {
            val measureResult = textMeasurer.measure(
                text = barre.toString(),
                style = TextStyle(
                    fontSize = barreFontSize,
                    fontWeight = FontWeight.Bold
                )
            )
            drawText(
                textLayoutResult = measureResult,
                topLeft = Offset(
                    x = leftOffsetPx - measureResult.size.width / 2f,
                    y = size.height - (bottomOffsetPx + measureResult.size.height) / 2
                )
            )
        }
    }
}


@Preview(showSystemUi = true)
@Composable
private fun FretBoardPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        FretBoard(
            chord = ChordModel(name = "Am", frets = "X O 7 6 6 6".split(" ")),
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}
