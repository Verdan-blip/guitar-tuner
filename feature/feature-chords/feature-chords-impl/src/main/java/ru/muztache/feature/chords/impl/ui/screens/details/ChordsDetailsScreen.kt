package ru.muztache.feature.chords.impl.ui.screens.details

import androidx.compose.foundation.layout.Box
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import ru.muztache.core.common.entity.FetchRequest
import ru.muztache.core.theme.MuztacheTheme
import ru.muztache.core.theme.composable.progress.MuztacheProgressIndicator
import ru.muztache.core.theme.composable.surface.MuztacheSurface
import ru.muztache.core.theme.composable.tab.MuztacheTabRow
import ru.muztache.core.theme.composable.tab.MuztacheTextTab
import ru.muztache.feature.chords.R
import ru.muztache.feature.chords.impl.ui.composable.FretBoard
import ru.muztache.feature.chords.impl.ui.entity.ChordModel
import ru.muztache.feature.chords.impl.ui.screens.details.mvi.ChordDetailsEvent
import ru.muztache.feature.chords.impl.ui.screens.details.mvi.ChordDetailsState

@Composable
internal fun ChordsDetailsScreen(
    modifier: Modifier = Modifier,
    chordDetailsType: ChordDetailsType
) {
    val viewModel = koinViewModel<ChordDetailsViewModel>()
    val state = viewModel.state.collectAsStateWithLifecycle()

    ChordDetailsScreenContent(
        title = when (chordDetailsType) {
            ChordDetailsType.BASIC -> {
                stringResource(R.string.basic_chords)
            }
            ChordDetailsType.ADVANCED -> {
                stringResource(R.string.advanced_chords)
            }
        },
        state = state.value,
        modifier = modifier
    )

    LaunchedEffect(Unit) {
        when (chordDetailsType) {
            ChordDetailsType.BASIC -> {
                viewModel.reducer(ChordDetailsEvent.LoadBasicChords)
            }
            ChordDetailsType.ADVANCED -> {
                viewModel.reducer(ChordDetailsEvent.LoadAdvancedChords)
            }
        }
    }
}

@Composable
private fun ChordDetailsScreenContent(
    modifier: Modifier = Modifier,
    title: String,
    state: ChordDetailsState
) {
    val selectedTab = remember { mutableIntStateOf(0) }
    when (state.chords) {
        is FetchRequest.Pending -> {
            Box(
                modifier = modifier
            ) {
                MuztacheProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }
        is FetchRequest.Failure -> {
            Box(
                modifier = modifier
            ) {
                Text(
                    text = stringResource(ru.muztache.core.common.R.string.error_while_loading_data),
                    style = MuztacheTheme.typography.bodyMedium,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }
        is FetchRequest.Success<*> -> {
            val data = state.chords.data as List<*>
            val chords = data.filterIsInstance<ChordModel>()
            Column(
                modifier = modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title,
                    style = MuztacheTheme.typography.displayLarge,
                    color = MuztacheTheme.colors.textPrimary,
                    modifier = Modifier
                        .padding(top = MuztacheTheme.paddings.extraLarge)
                )
                MuztacheTabRow(
                    selectedIndex = selectedTab.intValue,
                    modifier = Modifier
                        .padding(top = MuztacheTheme.paddings.extraLarge)
                        .padding(bottom = MuztacheTheme.paddings.normal)
                ) {
                    chords.forEachIndexed { index, chord ->
                        MuztacheTextTab(
                            selected = index == selectedTab.intValue,
                            text = chord.name,
                            onClick = { selectedTab.intValue = index },
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    FretBoard(
                        chord = chords[selectedTab.intValue],
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun ChordsDetailsScreenPreview() {
    MuztacheTheme {
        MuztacheSurface {
            ChordDetailsScreenContent(
                state = ChordDetailsState(
                    chords = FetchRequest.Success(
                        List(5) {
                            ChordModel("F", frets = "5 7 7 5 5 5".split(" "))
                        }
                    )
                ),
                title = "Базовые аккорды",
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }
}
