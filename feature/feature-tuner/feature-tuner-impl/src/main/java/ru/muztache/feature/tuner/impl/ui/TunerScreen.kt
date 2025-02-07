package ru.muztache.feature.tuner.impl.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import org.koin.androidx.compose.koinViewModel
import ru.muztache.core.theme.MuztacheTheme
import ru.muztache.feature.tuner.api.domain.entity.instrument.Guitar
import ru.muztache.feature.tuner.api.domain.entity.tone.Tone
import ru.muztache.feature.tuner.api.domain.entity.tone.ToneWithOctave
import ru.muztache.feature.tuner.api.domain.entity.tuning.GuitarTuning
import ru.muztache.feature.tuner.impl.ui.composable.PitchPanel
import ru.muztache.feature.tuner.impl.ui.composable.headstock.GuitarHeadstock
import ru.muztache.feature.tuner.impl.ui.composable.headstock.rememberHeadstockState
import ru.muztache.feature.tuner.impl.ui.composable.indicator.rememberProgressIndicatorState
import ru.muztache.feature.tuner.impl.ui.entity.Deviation
import ru.muztache.feature.tuner.impl.ui.entity.toFraction
import ru.muztache.feature.tuner.impl.ui.entity.toPercents
import ru.muztache.feature.tuner.impl.ui.mvi.Action
import ru.muztache.feature.tuner.impl.ui.mvi.Event
import ru.muztache.feature.tuner.impl.ui.mvi.State

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
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_START -> viewModel.onScreenVisible()
                Lifecycle.Event.ON_STOP -> viewModel.onScreenInvisible()
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
    state: State,
    onEvent: (Event) -> Unit,
    action: Action? = null
) {
    val tuningIndicatorState = rememberProgressIndicatorState()

    Box(
        modifier = modifier
    ) {
        PitchPanel(
            tuningIndicatorState = tuningIndicatorState,
            currentFrequency = state.currentFrequency,
            rootFrequency = state.idolNote.tone.rootFrequency,
            isTuned = state.isTuned,
            isAutoDetect = state.isAutoDetect,
            onAutoDetectSwitch = { onEvent(Event.AutoDetectSwitch) }
        )
        GuitarHeadstock(
            modifier = Modifier
                .align(Alignment.Center),
            tuning = state.selectedInstrument.tuning,
            headstockState = rememberHeadstockState(),
            onStringSelect = { stringNum ->
                onEvent(Event.StringSelect(stringNum))
            }
        )

        when (action) {
            is Action.OnNewDeviation -> {
                tuningIndicatorState.changeDeviationAnimation(
                    action.deviation.value
                        .toPercents()
                        .toFraction()
                )
            }
            else -> Unit
        }
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
            state = State(
                isTuned = false,
                isAutoDetect = false,
                isEnabled = false,
                selectedInstrument = Guitar(tuning = tuning),
                idolNote = instrument.getToneWithOctave(0),
                currentFrequency = 0f,
                selectedString = 0,
                shouldRequestPermission = true
            ),
            onEvent = { },
            action = Action.OnNewDeviation(deviation = Deviation(0f)),
            modifier = Modifier
                .fillMaxSize()
        )
    }
}
