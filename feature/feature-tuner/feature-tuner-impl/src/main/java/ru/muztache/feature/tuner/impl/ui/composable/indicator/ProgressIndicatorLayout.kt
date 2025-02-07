package ru.muztache.feature.tuner.impl.ui.composable.indicator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.max

@Composable
fun ProgressIndicatorLayout(
    modifier: Modifier = Modifier,
    state: TuningIndicatorState,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->

        val normalizedProgress = state.progress.value + 1f / 2
        var minHeight = 0

        val placeables = measurables.map { measurable ->
            measurable.measure(constraints).also {
                minHeight = max(minHeight, it.height)
            }
        }

        val newConstraints = constraints.copy(minHeight = minHeight)
        val layoutWidth = constraints.maxWidth

        layout(newConstraints.maxWidth, newConstraints.minHeight) {
            placeables.forEach { placeable ->
                val deltaToCenterY = (minHeight - placeable.height) / 2
                val centerPos = (layoutWidth - placeable.width) * normalizedProgress
                placeable.placeRelative(
                    x = centerPos.toInt(),
                    y = deltaToCenterY
                )
            }
        }
    }
}

@Preview
@Composable
private fun ProgressIndicatorLayoutPreview() {
    ProgressIndicatorLayout(
        state = rememberProgressIndicatorState(0f),
        content = {
            Box(
                modifier = Modifier
                    .size(24.dp, 24.dp)
                    .background(color = Color.Red, shape = CircleShape)
            )
        }
    )
}
