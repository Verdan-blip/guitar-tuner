package ru.muztache.feature.chords.impl.data.repository

import ru.muztache.core.data.local.chords.datasource.GuitarChordDataSource
import ru.muztache.feature.chords.api.domain.entity.Chord
import ru.muztache.feature.chords.api.domain.repository.GuitarChordRepository
import ru.muztache.feature.chords.impl.data.mapper.toChordEntityList
import ru.muztache.feature.chords.impl.data.mapper.toChordList

class GuitarChordRepositoryImpl(
    private val dataSource: GuitarChordDataSource
) : GuitarChordRepository {

    override suspend fun saveChords(chords: List<Chord>) {
        dataSource.insert(chords.toChordEntityList())
    }

    override suspend fun getBasicChords(): List<Chord> {
        return dataSource.getByNames(BASIC_CHORD_NAMES).toChordList()
    }

    override suspend fun getAdvancedChords(): List<Chord> {
        return dataSource.getByNames(ADVANCED_CHORD_NAMES).toChordList()
    }

    companion object {

        val BASIC_CHORD_NAMES = listOf("Am", "A", "Dm", "D", "C", "F", "G", "Em", "E")
        val ADVANCED_CHORD_NAMES = listOf("Cm", "C#m", "G#", "G#m", "Gm", "F#", "F#m", "B", "Bm", "A#", "A#m")
    }
}
