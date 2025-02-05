package ru.muztache.core.data.local.chords.mapper

import ru.muztache.core.data.GuitarChord
import ru.muztache.core.data.local.chords.entity.ChordEntity
import ru.muztache.core.data.local.chords.entity.FingeringType

internal fun GuitarChord.toChordEntity(): ChordEntity {
    val frets = frets.split(" ")
    if (frets.size != 6) error("Incorrect frets format")
    return ChordEntity(
        name = name,
        frets = frets.toFingeringTypesList()
    )
}

internal fun List<GuitarChord>.toChordEntityList(): List<ChordEntity> =
    map { chord -> chord.toChordEntity() }

internal fun List<String>.toFingeringTypesList(): List<FingeringType> =
    map { fret ->
        when (fret) {
            "X" -> FingeringType.Skip
            "O" -> FingeringType.Open
            else -> FingeringType.Use(fret.toInt())
        }
    }

internal fun List<FingeringType>.toFretsString(): String =
    joinToString(separator = " ") { type ->
        when (type) {
            is FingeringType.Open -> "O"
            is FingeringType.Skip -> "X"
            is FingeringType.Use -> type.fret.toString()
        }
    }