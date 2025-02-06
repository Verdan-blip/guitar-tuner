package ru.muztache.feature.chords.api.domain.repository

import ru.muztache.feature.chords.api.domain.entity.Chord

interface ReservedGuitarChordRepository {

    suspend fun getChords(): List<Chord>
}