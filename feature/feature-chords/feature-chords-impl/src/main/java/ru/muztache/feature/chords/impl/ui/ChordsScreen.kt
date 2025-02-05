package ru.muztache.feature.chords.impl.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import ru.muztache.core.common.entity.FetchRequest
import ru.muztache.core.theme.MuztacheTheme
import ru.muztache.core.theme.composable.progress.MuztacheProgressIndicator
import ru.muztache.core.theme.composable.surface.MuztacheSurface
import ru.muztache.feature.chords.R
import ru.muztache.feature.chords.impl.ui.composable.BasicChordsSection
import ru.muztache.feature.chords.impl.ui.entity.ChordModel
import ru.muztache.feature.chords.impl.ui.mvi.Event
import ru.muztache.feature.chords.impl.ui.mvi.State

@Composable
fun ChordsScreen(
    modifier: Modifier = Modifier
) {
    val viewModel = koinViewModel<ChordsViewModel>()
    val state = viewModel.state.collectAsStateWithLifecycle()
    val effect = viewModel.effect.collectAsStateWithLifecycle(null)

    ChordsScreenContent(
        state = state.value,
        onEvent = { event -> viewModel.reducer(event) },
        modifier = modifier
    )

    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(lifecycleOwner.lifecycle) {
        when (lifecycleOwner.lifecycle.currentState) {
            Lifecycle.State.CREATED -> {
                viewModel.reducer(Event.Load)
            }
            else -> Unit
        }
    }
}

@Composable
private fun ChordsScreenContent(
    state: State,
    onEvent: (Event) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.title_chords_library),
            style = MuztacheTheme.typography.displayLarge,
            color = MuztacheTheme.colors.textPrimary,
            modifier = Modifier
                .padding(top = MuztacheTheme.paddings.medium)
        )
        when (val request = state.baseChords) {
            is FetchRequest.Pending -> {
                MuztacheProgressIndicator()
            }
            is FetchRequest.Failure -> {
                Text(
                    text = stringResource(ru.muztache.core.common.R.string.unknown_error),
                    style = MuztacheTheme.typography.bodyMedium
                )
            }
            is FetchRequest.Success<*> -> {
                val chords = request.data
                val selectedChordIndex = remember { mutableIntStateOf(0) }
                if (chords is List<*>) {
                    val castedChords = chords.filterIsInstance<ChordModel>()
                    BasicChordsSection(
                        chords = castedChords,
                        selectedIndex = selectedChordIndex.intValue,
                        onChordTabClick = { index ->
                            selectedChordIndex.intValue = index
                        },
                        modifier = Modifier
                            .padding(top = MuztacheTheme.paddings.large)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun ChordsScreen() {
    MuztacheTheme {
        MuztacheSurface {
            ChordsScreenContent(
                state = State(
                    baseChords = FetchRequest.Success(
                        listOf(
                            ChordModel(name = "Am", frets = "X O 7 6 6 6".split(" ")),
                            ChordModel(name = "Am", frets = "X 8 7 7 7 6".split(" ")),
                            ChordModel(name = "Am", frets = "X O 7 6 6 6".split(" ")),
                            ChordModel(name = "Am", frets = "X O 7 6 6 6".split(" "))
                        )
                    )
                ),
                onEvent = { },
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }
}