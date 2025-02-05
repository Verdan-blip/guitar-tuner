package ru.muztache.feature.chords.impl.di

import org.koin.dsl.module
import ru.muztache.core.data.di.dataModule

val featureChordsModule = module {
    includes(dataModule)
}