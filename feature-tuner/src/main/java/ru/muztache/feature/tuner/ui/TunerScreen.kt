package ru.muztache.feature.tuner.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import org.koin.androidx.compose.koinViewModel
import ru.muztache.core.theme.MuztacheTheme
import ru.muztache.feature.tuner.domain.entity.instrument.Guitar
import ru.muztache.feature.tuner.domain.entity.tone.Tone
import ru.muztache.feature.tuner.domain.entity.tone.ToneWithOctave
import ru.muztache.feature.tuner.domain.entity.tuning.GuitarTuning
import ru.muztache.feature.tuner.ui.composable.headstock.GuitarHeadstock
import ru.muztache.feature.tuner.ui.composable.headstock.rememberHeadstockState
import ru.muztache.feature.tuner.ui.composable.indicator.ProgressIndicatorLayout
import ru.muztache.feature.tuner.ui.composable.indicator.rememberProgressIndicatorState
import ru.muztache.feature.tuner.ui.entity.math.Deviation
import ru.muztache.feature.tuner.ui.entity.math.toFraction
import ru.muztache.feature.tuner.ui.entity.math.toPercents
import ru.muztache.feature.tuner.ui.mvi.TunerAction
import ru.muztache.feature.tuner.ui.mvi.TunerEvent
import ru.muztache.feature.tuner.ui.mvi.TunerState

@Composable
fun TunerScreen(modifier: Modifier = Modifier) {

    val viewModel = koinViewModel<TunerViewModel>()

    val state = viewModel.state.collectAsState()
    val action = viewModel.action.collectAsState(null)

    TunerScreenContent(
        state = state.value,
        modifier = modifier,
        onEvent = { event -> viewModel.reducer(event) },
        action = action.value
    )

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(LocalLifecycleOwner.current) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> viewModel.onScreenVisible()
                Lifecycle.Event.ON_PAUSE -> viewModel.onScreenInvisible()
                else -> Unit
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }
}

@Composable
private fun TunerScreenContent(
    modifier: Modifier = Modifier,
    state: TunerState,
    onEvent: (TunerEvent) -> Unit,
    action: TunerAction? = null
) {
    val tuningIndicatorState = rememberProgressIndicatorState()

    when (action) {
        is TunerAction.OnNewDeviation -> {
            tuningIndicatorState.changeDeviationAnimation(
                action.deviation.value
                    .toPercents()
                    .toFraction()
            )
        }
        else -> Unit
    }

    Box(
        modifier = Modifier
            .padding(horizontal = MuztacheTheme.paddings.medium)
            .then(modifier)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
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
                            .size(48.dp, 48.dp)
                            .background(
                                color = if (state.isTuned)
                                    Color.Green
                                else
                                    Color.Red.copy(alpha = 0.5f),
                                shape = CircleShape
                            )
                    ) {
                        Text(
                            text = "%.1f".format(
                                tuningIndicatorState.progress.value
                            ),
                            color = Color.White,
                            modifier = Modifier
                                .align(Alignment.Center)
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(64.dp, 64.dp)
                        .border(
                            color = if (state.isTuned)
                                Color.Green
                            else
                                Color.Red.copy(alpha = 0.5f),
                            shape = CircleShape,
                            width = 4.dp
                        )
                )
            }
            Text(
                text = "%.1f of %.1f Hz".format(
                    state.currentFrequency,
                    state.idolNote.tone.rootFrequency
                ),
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(top = MuztacheTheme.paddings.large)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(top = MuztacheTheme.paddings.normal)
            ) {
                Text(
                    text = "Auto detect",
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(end = MuztacheTheme.paddings.normal)
                )
                Switch(
                    checked = state.isAutoDetect,
                    onCheckedChange = {
                        onEvent(TunerEvent.AutoDetectSwitch)
                    }
                )
            }
        }
        GuitarHeadstock(
            modifier = Modifier
                .align(Alignment.Center),
            tuning = state.selectedInstrument.tuning,
            headstockState = rememberHeadstockState(),
            onStringSelect = { stringNum ->
                onEvent(TunerEvent.StringSelect(stringNum))
            }
        )
        
    }
}

@Preview(showSystemUi = true)
@Composable
private fun TunerScreenContentPreview() {
    MuztacheTheme {
        val tuning = GuitarTuning(
            string1 = ToneWithOctave(Tone.E, 4),
            string2 = ToneWithOctave(Tone.B, 3),
            string3 = ToneWithOctave(Tone.G, 3),
            string4 = ToneWithOctave(Tone.D, 3),
            string5 = ToneWithOctave(Tone.A, 2),
            string6 = ToneWithOctave(Tone.E, 2)
        )
        val instrument = Guitar(tuning = tuning)
        TunerScreenContent(
            state = TunerState(
                isTuned = false,
                isAutoDetect = false,
                isEnabled = false,
                selectedInstrument = Guitar(
                    tuning = GuitarTuning(
                        string1 = ToneWithOctave(Tone.E, 4),
                        string2 = ToneWithOctave(Tone.B, 3),
                        string3 = ToneWithOctave(Tone.G, 3),
                        string4 = ToneWithOctave(Tone.D, 3),
                        string5 = ToneWithOctave(Tone.A, 2),
                        string6 = ToneWithOctave(Tone.E, 2)
                    )
                ),
                idolNote = instrument.getToneWithOctave(0),
                currentFrequency = 0f,
                selectedString = 0
            ),
            onEvent = { },
            action = TunerAction.OnNewDeviation(deviation = Deviation(0f)),
            modifier = Modifier
                .fillMaxSize()
        )
    }
}