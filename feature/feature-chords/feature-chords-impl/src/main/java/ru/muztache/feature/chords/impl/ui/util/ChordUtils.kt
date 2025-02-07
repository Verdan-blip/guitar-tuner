package ru.muztache.feature.chords.impl.ui.util

import androidx.core.text.isDigitsOnly
import ru.muztache.feature.chords.impl.ui.entity.ChordModel

fun calculateBarreValue(
    chord: ChordModel,
    maxFretDist: Int
): Int {
    var maxFret = 0
    chord.frets.forEach { fret ->
        if (fret.isDigitsOnly())
            maxFret = maxOf(maxFret, fret.toInt())
    }
    return (maxFret - maxFretDist).coerceAtLeast(0)
}
