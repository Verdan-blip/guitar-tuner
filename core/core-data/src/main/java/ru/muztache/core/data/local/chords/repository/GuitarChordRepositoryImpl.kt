package ru.muztache.core.data.local.chords.repository

import ru.muztache.core.data.local.chords.datasource.GuitarChordDataSource
import ru.muztache.core.data.local.chords.entity.ChordEntity

class GuitarChordRepositoryImpl(
    private val guitarChordDataSource: GuitarChordDataSource
) : ChordRepository {

    override suspend fun getBasicChords(): List<ChordEntity> {
        return guitarChordDataSource.getByNames(BASIC_CHORD_NAMES)
    }

    companion object {

        val BASIC_CHORD_NAMES = listOf("A", "Am", "C", "Em", "D", "Dm", "F")
    }
}