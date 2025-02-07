package ru.muztache.feature.chords.api.domain.repository

import ru.muztache.feature.chords.api.domain.entity.Chord

interface GuitarChordRepository {

    suspend fun saveChords(chords: List<Chord>)

    suspend fun getBasicChords(): List<Chord>

    suspend fun getAdvancedChords(): List<Chord>
}
