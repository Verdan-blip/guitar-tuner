package ru.muztache.feature.chords.impl.data.repository

import ru.muztache.feature.chords.api.domain.entity.Chord
import ru.muztache.feature.chords.api.domain.entity.FingeringType
import ru.muztache.feature.chords.api.domain.repository.ReservedGuitarChordRepository

class ReservedGuitarChordRepositoryImpl : ReservedGuitarChordRepository {

    override suspend fun getChords(): List<Chord> = listOf(
        //Basic
        Chord(name = "Am", frets = FingeringType.fromString("5 7 7 5 5 5")),
        Chord(name = "A", frets = FingeringType.fromString("5 7 7 6 5 5")),
        Chord(name = "Dm", frets = FingeringType.fromString("X 5 7 7 6 5")),
        Chord(name = "D", frets = FingeringType.fromString("X 5 7 7 7 5")),
        Chord(name = "C", frets = FingeringType.fromString("8 10 10 9 8 8")),
        Chord(name = "F", frets = FingeringType.fromString("1 3 3 2 1 1")),
        Chord(name = "Em", frets = FingeringType.fromString("X 7 9 9 8 7")),
        Chord(name = "E", frets = FingeringType.fromString("X 7 9 9 9 7")),
        //Advanced
        Chord(name = "Cm", frets = FingeringType.fromString("X 3 5 5 4 3")),
        Chord(name = "C#m", frets = FingeringType.fromString("X 4 6 6 5 4")),
        Chord(name = "G#", frets = FingeringType.fromString("4 6 6 5 4 4")),
        Chord(name = "G#m", frets = FingeringType.fromString("4 6 6 4 4 4")),
        Chord(name = "Gm", frets = FingeringType.fromString("3 5 5 3 3 3")),
        Chord(name = "F#", frets = FingeringType.fromString("2 4 4 3 2 2")),
        Chord(name = "F#m", frets = FingeringType.fromString("2 4 4 2 2 2")),
        Chord(name = "B", frets = FingeringType.fromString("X 2 4 4 4 2")),
        Chord(name = "Bm", frets = FingeringType.fromString("X 2 4 4 3 2")),
        Chord(name = "A#", frets = FingeringType.fromString("X 1 3 3 3 1")),
        Chord(name = "A#m", frets = FingeringType.fromString("X 1 3 3 2 1"))
    )
}
