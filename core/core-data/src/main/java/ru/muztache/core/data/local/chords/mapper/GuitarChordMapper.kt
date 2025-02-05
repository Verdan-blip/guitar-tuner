package ru.muztache.core.data.local.chords.mapper

import ru.muztache.core.data.GuitarChord
import ru.muztache.core.data.local.chords.entity.ChordEntity

internal fun GuitarChord.toChordEntity(): ChordEntity =
    ChordEntity(
        name = name,
        frets = frets.split(" ")
    )

internal fun List<GuitarChord>.toGuitarChordEntityList(): List<ChordEntity> =
    map { guitarChord -> guitarChord.toChordEntity() }

internal fun List<String>.toFretString(): String =
    joinToString(separator = " ")