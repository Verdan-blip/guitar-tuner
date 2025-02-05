package ru.muztache.feature.chords.api.domain.repository

import ru.muztache.feature.chords.api.domain.entity.Chord

interface GuitarChordsRepository {

    suspend fun getBaseChords(): List<Chord>
}