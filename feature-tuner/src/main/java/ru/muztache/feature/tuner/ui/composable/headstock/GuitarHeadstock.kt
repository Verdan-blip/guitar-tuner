package ru.muztache.feature.tuner.ui.composable.headstock

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import ru.muztache.core.common.compose.asOffset
import ru.muztache.core.common.compose.asPxValue
import ru.muztache.core.common.compose.asSize
import ru.muztache.core.common.compose.layoutPositionsFromCenter
import ru.muztache.core.common.compose.onIntersect
import ru.muztache.feature.tuner.domain.entity.tone.Tone
import ru.muztache.feature.tuner.ui.entity.impl.guitar.GuitarStandardTuning
import ru.muztache.feature.tuner.domain.entity.tuning.Tuning

private val headstockDpSize: DpSize = DpSize(156.dp, 256.dp)

private val upperCornerRadiusDp: Dp = 16.dp
private val lowerCornerRadiusDp: Dp = 32.dp

private val pegHoleRadiusDp: Dp = 18.dp

private val pegDpSize: DpSize = DpSize(16.dp, 32.dp)
private val pegKernelDpSize: DpSize = DpSize(6.dp, 10.dp)
private val pegLayoutMatrix = IntSize(2, 3)

private val distBetweenPegHolesDp: DpSize = DpSize(52.dp, 32.dp)
private val distBetweenPegs: DpSize = DpSize(headstockDpSize.width, 36.dp)

private val sillHeightDp: Dp = 12.dp

private val guitarNeckDpSize: DpSize = DpSize(96.dp, 96.dp)

private val stringWidthDp: Dp = 2.dp
private val stringPaddingHorizontalDp: Dp = 8.dp

data class GuitarColors(
    val woodColor: Color,
    val pegHoleColor: Color,
    val pegHoleSelectedColor: Color,
    val pegColor: Color,
    val toneTextColor: Color,
    val sillColor: Color,
    val stringColor: Color,
    val selectedStringColor: Color
)

val GuitarColorsDefaults = GuitarColors(
    woodColor = Color(171, 104, 58),
    pegHoleColor = Color.White,
    pegHoleSelectedColor = Color(236, 198, 121, 255),
    pegColor = Color.LightGray,
    toneTextColor = Color(171, 104, 58),
    sillColor = Color.LightGray,
    stringColor = Color.LightGray,
    selectedStringColor = Color(236, 198, 121, 255)
)

@Composable
fun GuitarHeadstock(
    modifier: Modifier = Modifier,
    headstockState: HeadstockState,
    onStringSelect: (Int) -> Unit,
    toneTextSize: TextUnit = TextUnit.Unspecified,
    guitarColors: GuitarColors = GuitarColorsDefaults,
    tuning: Tuning
) {
    val headstockSize = headstockDpSize.asSize
    val pegSize = pegDpSize.asSize
    val pegKernelSize = pegKernelDpSize.asSize

    val headstockCenter = Offset(
        x = pegKernelSize.width + pegSize.width + headstockSize.width / 2f,
        y = headstockSize.height / 2f
    )

    val pegHoleRadiusPx = pegHoleRadiusDp.asPxValue

    val distBetweenPegHoles = distBetweenPegHolesDp.asOffset
    val distBetweenPegs = distBetweenPegs.asOffset

    val pegHolePositions = remember {
        layoutPositionsFromCenter(
            center = headstockCenter,
            itemPaddingBetween = distBetweenPegHoles,
            itemSize = Size(
                width = pegHoleRadiusPx * 2f,
                height = pegHoleRadiusPx * 2f
            ),
            layoutMatrix = pegLayoutMatrix
        )
    }

    val pegPositions = remember {
        layoutPositionsFromCenter(
            center = headstockCenter,
            itemPaddingBetween = distBetweenPegs,
            itemSize = Size(
                width = pegSize.width + pegKernelSize.width,
                height = pegSize.height
            ),
            layoutMatrix = pegLayoutMatrix
        )
    }

    val selectedString = headstockState.selectedString.value

    val textMeasurer = rememberTextMeasurer()

    Canvas(
        modifier = Modifier
            .requiredWidth(
                headstockDpSize.width + pegKernelDpSize.width * 2 + pegDpSize.width * 2
            )
            .requiredHeight(
                headstockDpSize.height + guitarNeckDpSize.height
            )
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { pos ->
                        onIntersect(
                            target = pos,
                            points = pegHolePositions,
                            pointRadius = pegHoleRadiusPx,
                            callback = { stringNum ->
                                headstockState.selectedString.intValue = stringNum
                                onStringSelect(stringNum)
                            }
                        )
                    }
                )
            }
            .then(modifier)
    ) {
        val upperCornerRadiusPx = upperCornerRadiusDp.toPx()
        val lowerCornerRadiusPx = lowerCornerRadiusDp.toPx()

        val upperCornerRadius = CornerRadius(upperCornerRadiusPx)
        val lowerCornerRadius = CornerRadius(lowerCornerRadiusPx)

        val guitarNeckSize = guitarNeckDpSize.toSize()

        val sillHeightPx = sillHeightDp.toPx()

        val path = Path().apply {
            addRoundRect(
                roundRect = RoundRect(
                    left = pegSize.width + pegKernelSize.width,
                    top = 0f,
                    right = headstockSize.width + pegKernelSize.width + pegSize.width,
                    bottom = headstockSize.height,
                    topLeftCornerRadius = upperCornerRadius,
                    topRightCornerRadius = upperCornerRadius,
                    bottomLeftCornerRadius = lowerCornerRadius,
                    bottomRightCornerRadius = lowerCornerRadius
                )
            )
        }
        drawPath(
            path = path,
            color = guitarColors.woodColor
        )
        drawGuitarNeck(
            neckSize = guitarNeckSize,
            topLeft = Offset(
                x = size.width / 2f - guitarNeckSize.width / 2f,
                y = headstockSize.height
            ),
            color = guitarColors.woodColor
        )
        drawSill(
            topLeft = Offset(
                x = size.width / 2f - guitarNeckSize.width / 2f,
                y = headstockSize.height
            ),
            size = Size(
                width = guitarNeckSize.width,
                height = sillHeightPx
            ),
            color = guitarColors.sillColor
        )

        val stringWidth = stringWidthDp.toPx()
        val paddingBetweenStrings = guitarNeckSize.width / 7

        pegHolePositions.forEachIndexed { index, holePosition ->
            val sillIntersectionPos = Offset(
                x = headstockCenter.x + guitarNeckSize.width / 2f - (index + 1) * paddingBetweenStrings,
                y = headstockSize.height
            )
            val stringPath = Path().apply {
                moveTo(
                    x = holePosition.x + pegHoleRadiusPx,
                    y = holePosition.y + pegHoleRadiusPx
                )
                lineTo(
                    x = sillIntersectionPos.x,
                    y = sillIntersectionPos.y
                )
                lineTo(
                    x = sillIntersectionPos.x,
                    y = headstockSize.height + guitarNeckSize.height
                )
            }
            drawPath(
                path = stringPath,
                color = if (index == selectedString)
                    guitarColors.selectedStringColor
                else
                    guitarColors.stringColor,
                style = Stroke(width = stringWidth)
            )
        }

        pegHolePositions.forEachIndexed { stringNum, topLeft ->
            drawPegHole(
                tone = tuning.getNote(stringNum),
                textSize = toneTextSize,
                textColor = guitarColors.toneTextColor,
                textMeasurer = textMeasurer,
                topLeft = topLeft,
                radius = pegHoleRadiusPx,
                color = if (stringNum == selectedString)
                    guitarColors.pegHoleSelectedColor
                else
                    guitarColors.pegHoleColor
            )
        }
        pegPositions.forEachIndexed { stringNum, topLeft ->
            val isFlip = stringNum < pegPositions.size / 2
            drawPeg(
                topLeft = topLeft,
                pegSize = pegSize,
                pegKernelSize = pegKernelSize,
                isFlip = isFlip,
                color = guitarColors.pegColor
            )
        }
    }
}

fun DrawScope.drawPeg(
    topLeft: Offset,
    pegSize: Size,
    pegKernelSize: Size,
    isFlip: Boolean = false,
    color: Color
) {
    drawOval(
        color = color,
        topLeft = if (isFlip)
            topLeft + Offset(
                x = pegKernelSize.width,
                y = 0f
            )
        else
            topLeft,
        size = pegSize
    )
    drawRect(
        color = color,
        topLeft = if (isFlip)
            topLeft + Offset(
                x = 0f,
                y = pegSize.height / 2f - pegKernelSize.height / 2f
            )
        else
            topLeft + Offset(
                x = pegSize.width,
                y = pegSize.height / 2f - pegKernelSize.height / 2f
            ),
        size = pegKernelSize
    )
}

fun DrawScope.drawPegHole(
    tone: Tone,
    textSize: TextUnit = TextUnit.Unspecified,
    textColor: Color,
    textMeasurer: TextMeasurer,
    topLeft: Offset,
    radius: Float,
    color: Color
) {
    val measureResult = textMeasurer.measure(
        text = tone.name,
        style = TextStyle(
            color = textColor,
            fontSize = textSize,
            fontWeight = FontWeight.SemiBold
        )
    )
    drawCircle(
        color = color,
        radius = radius,
        center = topLeft + Offset(radius, radius)
    )
    drawText(
        measureResult,
        topLeft = topLeft + Offset(radius, radius) - Offset(
            x = measureResult.size.width / 2f,
            y = measureResult.size.height / 2f
        )
    )
}

fun DrawScope.drawGuitarNeck(
    neckSize: Size,
    topLeft: Offset,
    color: Color
) {
    drawRect(
        color = color,
        topLeft = topLeft,
        size = neckSize
    )
}

fun DrawScope.drawSill(
    topLeft: Offset,
    size: Size,
    color: Color
) {
    drawRect(
        topLeft = topLeft,
        color = color,
        size = size
    )
}

@Preview
@Composable
private fun GuitarHeadstockPreview() {
    GuitarHeadstock(
        tuning = GuitarStandardTuning,
        headstockState = rememberHeadstockState(),
        onStringSelect = { }
    )
}