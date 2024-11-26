package ru.muztache.feature.tuner.ui.composable

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import ru.muztache.core.common.compose.asPxValue

data class PitchProgressIndicatorColors(
    val incorrectProgress: Color,
    val correctProgress: Color,
    val partlyCorrectProgress: Color,
    val placeholder: Color
)

val PitchProgressIndicatorColorsDefaults = PitchProgressIndicatorColors(
    incorrectProgress = Color.Red,
    correctProgress = Color.Green,
    partlyCorrectProgress = Color.Yellow,
    placeholder = Color.Gray
)

private enum class ConfigurationStatus {
    CONFIGURED_FULLY, CONFIGURED_PARTLY, NOT_CONFIGURED
}

@Composable
fun PitchProgressIndicator(
    modifier: Modifier = Modifier,
    progress: Float,
    dotCount: Int = 5,
    dotRadius: Dp = 8.dp,
    distanceBetweenDots: Dp = 8.dp,
    colors: PitchProgressIndicatorColors = PitchProgressIndicatorColorsDefaults
) {
    require(progress in -0.5f..0.5f) {
        "Progress must be in range [-0.5, 0.5]"
    }

    val dotRadiusPx = dotRadius.asPxValue
    val distanceBetweenDotsPx = distanceBetweenDots.asPxValue

    val configurationStatus = when (progress) {
        in -0.1f..0.1f -> ConfigurationStatus.CONFIGURED_FULLY
        in -0.2f..0.2f -> ConfigurationStatus.CONFIGURED_PARTLY
        else -> ConfigurationStatus.NOT_CONFIGURED
    }

    Canvas(
        modifier = Modifier
            .width(dotRadius * 2 * dotCount + (dotCount - 1) * distanceBetweenDots)
            .height(dotRadius * 2)
            .then(modifier)
    ) {
        repeat(dotCount) { index ->
            val ratio = index.toFloat() / dotCount - 0.5f
            drawCircle(
                color = when (ratio) {
                    in -0.1f..0.1f -> {
                        when (configurationStatus) {
                            ConfigurationStatus.CONFIGURED_FULLY -> colors.correctProgress
                            ConfigurationStatus.CONFIGURED_PARTLY -> colors.partlyCorrectProgress
                            ConfigurationStatus.NOT_CONFIGURED -> colors.placeholder
                        }
                    }
                    in -0.2f..0.2f -> {
                        colors.incorrectProgress
                    }
                    in 0.1f..0.5f -> {
                        colors.incorrectProgress
                    }
                    else -> colors.placeholder
                },
                radius = dotRadiusPx,
                center = Offset(
                    x = index * dotRadiusPx * 2 + dotRadiusPx + index * distanceBetweenDotsPx,
                    y = dotRadiusPx
                )
            )
        }
    }
}

@Preview
@Composable
private fun PitchProgressIndicatorPreview() {
    PitchProgressIndicator(
        progress = 0.0f,
        dotCount = 13,
        distanceBetweenDots = 16.dp
    )
}