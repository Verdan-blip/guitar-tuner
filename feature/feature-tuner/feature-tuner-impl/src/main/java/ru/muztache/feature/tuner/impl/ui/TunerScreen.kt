package ru.muztache.feature.tuner.impl.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import org.koin.androidx.compose.koinViewModel
import ru.muztache.core.common.compose.LifecycleEventListener
import ru.muztache.core.common.entity.FetchRequest
import ru.muztache.core.theme.MuztacheTheme
import ru.muztache.core.theme.composable.dropdown.MuztacheDropdownMenuBox
import ru.muztache.core.theme.composable.progress.MuztacheProgressIndicator
import ru.muztache.core.theme.composable.surface.MuztacheSurface
import ru.muztache.feature.tuner.api.domain.entity.tone.Tone
import ru.muztache.feature.tuner.api.domain.entity.tone.ToneWithOctave
import ru.muztache.feature.tuner.impl.R
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

    LaunchedEffect(Unit) {
        viewModel.reducer(Event.Load)
    }

    LifecycleEventListener { event ->
        when (event) {
            Lifecycle.Event.ON_START -> {
                viewModel.reducer(Event.ScreenEntered)
            }
            Lifecycle.Event.ON_STOP -> {
                viewModel.reducer(Event.ScreenExited)
            }
            else -> Unit
        }
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

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val headstockState = rememberHeadstockState()

        PitchPanel(
            tuningIndicatorState = tuningIndicatorState,
            currentFrequency = state.currentFrequency,
            rootFrequency = state.idolNote.tone.rootFrequency,
            isTuned = state.isTuned,
            isAutoDetect = state.isAutoDetect,
            onAutoDetectSwitch = { onEvent(Event.AutoDetectSwitch) }
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            when (val result = state.selectedKey) {
                is FetchRequest.Pending -> {
                    MuztacheProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }
                is FetchRequest.Failure -> {
                    Text(
                        text = stringResource(R.string.failed_to_fetch_guitars),
                        style = MuztacheTheme.typography.titleMedium,
                        color = MuztacheTheme.colors.textPrimary,
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }
                is FetchRequest.Success<*> -> {
                    val selectedKey = result.data as String
                    state.savedTunings[selectedKey]?.apply {
                        Column(
                            modifier = Modifier
                                .align(Alignment.Center),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            GuitarHeadstock(
                                tuning = tuning,
                                headstockState = headstockState,
                                onStringSelect = { stringNum ->
                                    onEvent(Event.StringSelect(stringNum))
                                }
                            )
                            val menuExpanded = remember {
                                mutableStateOf(false)
                            }
                            MuztacheDropdownMenuBox(
                                expanded = menuExpanded.value,
                                onExpandedChange = { expanded ->
                                    menuExpanded.value = expanded
                                },
                                onDismissItem = {
                                    menuExpanded.value = false
                                },
                                onItemClick = { key ->
                                    onEvent(Event.SelectInstrument(key))
                                    menuExpanded.value = false
                                },
                                label = stringResource(R.string.your_guitar_tunings),
                                options = state.savedTunings.keys.toList(),
                                selectedText = selectedKey,
                                modifier = Modifier
                                    .padding(top = MuztacheTheme.paddings.large)
                            )
                        }
                    }
                }
            }
        }
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
        MuztacheSurface {
            TunerScreenContent(
                state = State(
                    isTuned = false,
                    isAutoDetect = false,
                    isEnabled = false,
                    selectedKey = FetchRequest.Pending,
                    idolNote = ToneWithOctave(Tone.UNRECOGNIZED, 0),
                    currentFrequency = 0f,
                    selectedString = 0
                ),
                onEvent = { },
                action = Action.OnNewDeviation(deviation = Deviation(0f)),
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }
}
