package ru.muztache.feature.tuner.impl.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ru.muztache.core.theme.MuztacheTheme
import ru.muztache.core.theme.composable.sswitch.MuztacheSwitch
import ru.muztache.feature.tuner.impl.R
import ru.muztache.feature.tuner.impl.ui.composable.indicator.ProgressIndicatorLayout
import ru.muztache.feature.tuner.impl.ui.composable.indicator.TuningIndicatorState
import ru.muztache.feature.tuner.impl.ui.composable.indicator.rememberProgressIndicatorState

@Composable
fun PitchPanel(
    modifier: Modifier = Modifier,
    tuningIndicatorState: TuningIndicatorState,
    currentFrequency: Float,
    rootFrequency: Float,
    isTuned: Boolean,
    isAutoDetect: Boolean,
    onAutoDetectSwitch: (Boolean) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .padding(top = MuztacheTheme.paddings.extraLarge)
        ) {
            ProgressIndicatorLayout(
                state = tuningIndicatorState,
                modifier = Modifier
                    .align(Alignment.Center)
            ) {
                Box(
                    modifier = Modifier
                        .size(MuztacheTheme.sizes.normal)
                        .background(
                            color = if (isTuned)
                                MuztacheTheme.colors.primary
                            else
                                MuztacheTheme.colors.error.copy(alpha = 0.5f),
                            shape = CircleShape
                        )
                ) {
                    Text(
                        text = "%.1f".format(
                            tuningIndicatorState.progress.value
                        ),
                        color = MuztacheTheme.colors.onPrimary,
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }
            }
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(MuztacheTheme.sizes.medium)
                    .border(
                        color = if (isTuned)
                            MuztacheTheme.colors.primary
                        else
                            MuztacheTheme.colors.error.copy(alpha = 0.5f),
                        shape = CircleShape,
                        width = MuztacheTheme.paddings.small
                    )
            )
        }
        Text(
            text = "%.1f of %.1f Hz".format(currentFrequency, rootFrequency),
            style = MuztacheTheme.typography.bodyMedium,
            modifier = Modifier
                .padding(top = MuztacheTheme.paddings.large)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(top = MuztacheTheme.paddings.normal)
        ) {
            Text(
                text = stringResource(R.string.auto_detect),
                style = MuztacheTheme.typography.bodyLarge,
                color = MuztacheTheme.colors.textSecondary,
                modifier = Modifier
                    .padding(end = MuztacheTheme.paddings.normal)
            )
            MuztacheSwitch(
                checked = isAutoDetect,
                onCheckedChange = onAutoDetectSwitch
            )
        }
    }
}

@Preview
@Composable
private fun PitchPanelPreview() {
    MuztacheTheme {
        PitchPanel(
            tuningIndicatorState = rememberProgressIndicatorState(),
            currentFrequency = 0f,
            rootFrequency = 0f,
            isTuned = false,
            isAutoDetect = false,
            onAutoDetectSwitch = { }
        )
    }
}
