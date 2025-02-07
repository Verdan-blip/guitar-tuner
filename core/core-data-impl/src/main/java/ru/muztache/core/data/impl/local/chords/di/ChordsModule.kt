package ru.muztache.core.data.impl.local.chords.di

import org.koin.dsl.module
import ru.muztache.core.data.impl.GuitarTunerDatabase
import ru.muztache.core.data.impl.local.chords.datasource.GuitarChordDataSourceImpl
import ru.muztache.core.data.local.chords.datasource.GuitarChordDataSource

internal val chordsModule = module {
    factory<GuitarChordDataSource> {
        GuitarChordDataSourceImpl(get<GuitarTunerDatabase>().guitarChordQueries)
    }
}
