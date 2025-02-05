package ru.muztache.feature.chords.impl.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.muztache.core.theme.MuztacheTheme
import ru.muztache.core.theme.composable.surface.MuztacheSurface
import ru.muztache.core.theme.composable.tab.MuztacheTabRow
import ru.muztache.core.theme.composable.tab.MuztacheTextTab
import ru.muztache.feature.chords.impl.ui.entity.ChordModel

@Composable
fun BasicChordsSection(
    modifier: Modifier = Modifier,
    selectedIndex: Int = 0,
    onChordTabClick: (Int) -> Unit,
    chords: List<ChordModel>
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MuztacheTabRow(
            selectedIndex = selectedIndex,
            modifier = Modifier
                .padding(bottom = MuztacheTheme.paddings.normal)
        ) {
            chords.forEachIndexed { index, chord ->
                MuztacheTextTab(
                    selected = index == selectedIndex,
                    text = chord.name,
                    onClick = { onChordTabClick(index) },
                )
            }
        }
        FretBoard(
            chord = chords[selectedIndex]
        )
    }
}

@Preview
@Composable
private fun BasicChordsSectionPreview() {
    MuztacheTheme {
        MuztacheSurface {
            BasicChordsSection(
                chords = List(5) {
                    ChordModel("F", frets = "5 7 7 5 5 5".split(" "))
                },
                onChordTabClick = {  }
            )
        }
    }
}