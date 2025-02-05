package ru.muztache.feature.chords.impl.ui.mapper

import ru.muztache.feature.chords.api.domain.entity.Chord
import ru.muztache.feature.chords.impl.ui.entity.ChordModel

internal fun Chord.toChordModel(): ChordModel =
    ChordModel(
        name = name,
        frets = frets.map { fret -> fret.toString() }
    )

internal fun List<Chord>.toChordModelList(): List<ChordModel> =
    map { chord -> chord.toChordModel() }