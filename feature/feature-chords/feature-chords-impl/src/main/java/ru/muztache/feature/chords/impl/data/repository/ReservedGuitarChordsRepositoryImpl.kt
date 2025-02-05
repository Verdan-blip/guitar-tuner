package ru.muztache.feature.chords.impl.data.repository

import ru.muztache.feature.chords.api.domain.entity.Chord
import ru.muztache.feature.chords.api.domain.entity.FingeringType
import ru.muztache.feature.chords.api.domain.repository.ReservedGuitarChordRepository

class ReservedGuitarChordsRepositoryImpl : ReservedGuitarChordRepository {

    override suspend fun getBasicChords(): List<Chord> = listOf(
        Chord(name = "Am", frets = FingeringType.fromString("5 7 7 5 5 5")),
        Chord(name = "A", frets = FingeringType.fromString("5 7 7 6 5 5")),
        Chord(name = "Dm", frets = FingeringType.fromString("X 5 7 7 6 5")),
        Chord(name = "D", frets = FingeringType.fromString("X 5 7 7 7 5")),
        Chord(name = "C", frets = FingeringType.fromString("8 10 10 9 8 8")),
        Chord(name = "F", frets = FingeringType.fromString("1 3 3 2 1 1")),
        Chord(name = "Em", frets = FingeringType.fromString("X 7 9 9 8 7")),
        Chord(name = "E", frets = FingeringType.fromString("X 7 9 9 9 7"))
    )
}