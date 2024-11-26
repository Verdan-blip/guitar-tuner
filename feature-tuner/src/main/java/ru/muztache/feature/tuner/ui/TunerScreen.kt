package ru.muztache.feature.tuner.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import org.koin.androidx.compose.koinViewModel
import ru.muztache.feature.tuner.ui.composable.NoteIndicator
import ru.muztache.feature.tuner.ui.composable.StringIndicator
import ru.muztache.feature.tuner.ui.engine.tone.Tone
import ru.muztache.feature.tuner.ui.entity.impl.guitar.Guitar
import ru.muztache.feature.tuner.ui.mvi.TunerEvent
import ru.muztache.feature.tuner.ui.mvi.TunerState

@Composable
fun TunerScreen(modifier: Modifier = Modifier) {

    val viewModel = koinViewModel<TunerViewModel>()
    val state = viewModel.state.collectAsState()
    val onEvent: (event: TunerEvent) -> Unit = { event ->
        viewModel.reducer(event)
    }

    TunerScreenContent(
        state = state.value,
        modifier = modifier,
        onEvent = onEvent
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
    state: TunerState,
    onEvent: (TunerEvent) -> Unit
) {
    Box(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
        ) {
            NoteIndicator(
                radius = 128.dp,
                note = state.idolNote,
                isCorrect = state.isCorrect,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = "${state.currentDiff}",
                fontSize = 24.sp,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp)
        ) {
            repeat(state.selectedInstrument.stringCount) { index ->
                StringIndicator(
                    modifier = Modifier,
                    radius = 24.dp,
                    tailLength = 48.dp,
                    note = state.selectedInstrument.getTone(index),
                    isSelected = index == state.selectedString,
                    onClick = { onEvent(TunerEvent.StringSelect(index)) }
                )
                Spacer(
                    modifier = Modifier
                        .width(8.dp)
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun TunerScreenContentPreview() {
    TunerScreenContent(
        state = TunerState(
            isCorrect = false,
            isEnabled = false,
            selectedInstrument = Guitar(),
            idolNote = Tone.B4,
            currentDiff = 0f,
            selectedString = 0
        ),
        onEvent = { },
        modifier = Modifier
            .fillMaxSize()
    )
}