package ru.muztache.core.data.local.chords.datasource

import ru.muztache.core.data.local.chords.entity.ChordEntity

interface GuitarChordDataSource {

    suspend fun getAll(): List<ChordEntity>

    suspend fun getByName(name: String): List<ChordEntity>

    suspend fun getByNames(names: List<String>): List<ChordEntity>

    suspend fun insert(chord: ChordEntity)

    suspend fun insert(chords: List<ChordEntity>)
}