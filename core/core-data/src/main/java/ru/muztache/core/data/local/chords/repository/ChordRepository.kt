package ru.muztache.core.data.local.chords.repository

import ru.muztache.core.data.local.chords.entity.ChordEntity

interface ChordRepository {

    suspend fun getBasicChords(): List<ChordEntity>
}