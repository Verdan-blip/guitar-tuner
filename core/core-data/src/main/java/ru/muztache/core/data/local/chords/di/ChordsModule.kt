package ru.muztache.core.data.local.chords.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.muztache.core.data.local.chords.datasource.GuitarChordDataSource
import ru.muztache.core.data.local.chords.datasource.GuitarChordDataSourceImpl
import ru.muztache.core.data.local.chords.repository.ChordRepository
import ru.muztache.core.data.local.chords.repository.GuitarChordRepositoryImpl

internal val chordsModule = module {
    factoryOf(::GuitarChordDataSourceImpl) bind GuitarChordDataSource::class
    factoryOf(::GuitarChordRepositoryImpl) bind ChordRepository::class
}