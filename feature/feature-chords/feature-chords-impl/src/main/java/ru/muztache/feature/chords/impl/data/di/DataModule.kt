package ru.muztache.feature.chords.impl.data.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.muztache.feature.chords.api.domain.repository.GuitarChordRepository
import ru.muztache.feature.chords.api.domain.repository.ReservedGuitarChordRepository
import ru.muztache.feature.chords.impl.data.repository.GuitarChordRepositoryImpl
import ru.muztache.feature.chords.impl.data.repository.ReservedGuitarChordRepositoryImpl

internal val dataModule = module {
    factoryOf(::ReservedGuitarChordRepositoryImpl) bind ReservedGuitarChordRepository::class
    factoryOf(::GuitarChordRepositoryImpl) bind GuitarChordRepository::class
}