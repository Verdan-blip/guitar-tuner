package ru.muztache.core.data.impl.local.chords.datasource

import ru.muztache.core.data.impl.GuitarChordQueries
import ru.muztache.core.data.local.chords.datasource.GuitarChordDataSource
import ru.muztache.core.data.local.chords.entity.ChordEntity
import ru.muztache.core.data.impl.local.chords.mapper.toFretString
import ru.muztache.core.data.impl.local.chords.mapper.toGuitarChordEntityList

class GuitarChordDataSourceImpl(
    private val queries: GuitarChordQueries
) : GuitarChordDataSource {

    override suspend fun getAll(): List<ChordEntity> {
        return queries.transactionWithResult {
            queries.selectAll()
                .executeAsList()
                .toGuitarChordEntityList()
        }
    }

    override suspend fun getByName(name: String): List<ChordEntity> {
        return queries.transactionWithResult {
            queries.selectByName(name)
                .executeAsList()
                .toGuitarChordEntityList()
        }
    }

    override suspend fun getByNames(names: List<String>): List<ChordEntity> {
        return queries.transactionWithResult {
            queries.selectByNames(names)
                .executeAsList()
                .toGuitarChordEntityList()
        }
    }

    override suspend fun insert(chord: ChordEntity) {
        queries.transaction {
            queries.insert(chord.name, chord.frets.toFretString())
        }
    }

    override suspend fun insert(chords: List<ChordEntity>) {
        queries.transaction {
            chords.forEach { chord ->
                queries.insert(chord.name, chord.frets.toFretString())
            }
        }
    }
}