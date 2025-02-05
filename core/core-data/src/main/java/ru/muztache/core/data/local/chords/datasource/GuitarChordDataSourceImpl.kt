package ru.muztache.core.data.local.chords.datasource

import ru.muztache.core.data.GuitarChordQueries
import ru.muztache.core.data.local.chords.entity.ChordEntity
import ru.muztache.core.data.local.chords.mapper.toFretString
import ru.muztache.core.data.local.chords.mapper.toGuitarChordEntityList

class GuitarChordDataSourceImpl(
    private val queries: GuitarChordQueries
) : GuitarChordDataSource {

    override suspend fun getAll(): List<ChordEntity> {
        return queries.selectAll()
            .executeAsList()
            .toGuitarChordEntityList()
    }

    override suspend fun getByName(name: String): List<ChordEntity> {
        return queries.selectByName(name)
            .executeAsList()
            .toGuitarChordEntityList()
    }

    override suspend fun getByNames(names: List<String>): List<ChordEntity> {
        return queries.selectByNames(names)
            .executeAsList()
            .toGuitarChordEntityList()
    }

    override suspend fun insert(chord: ChordEntity) {
        queries.insert(chord.name, chord.frets.toFretString())
    }
}