package ru.muztache.feature.chords.impl.data.mapper

import ru.muztache.core.data.local.chords.entity.ChordEntity
import ru.muztache.feature.chords.api.domain.entity.Chord
import ru.muztache.feature.chords.api.domain.entity.FingeringType

fun ChordEntity.toChord(): Chord = Chord(
    name = name,
    frets = frets.map { fret -> fret.toFingeringType() }
)

fun Chord.toChordEntity(): ChordEntity = ChordEntity(
    name = name,
    frets = frets.map { fret -> fret.toFretString() }
)

fun List<ChordEntity>.toChordList(): List<Chord> = map { chord ->
    chord.toChord()
}

fun List<Chord>.toChordEntityList(): List<ChordEntity> = map { chord ->
    chord.toChordEntity()
}

internal fun String.toFingeringType(): FingeringType =
    when (this) {
        "X" -> FingeringType.X
        "O" -> FingeringType.O
        else -> FingeringType.Use(toInt())
    }

internal fun FingeringType.toFretString(): String =
    when (this) {
        is FingeringType.X -> "X"
        is FingeringType.O -> "O"
        is FingeringType.Use -> fret.toString()
    }