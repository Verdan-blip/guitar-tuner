package ru.muztache.feature.tuner.data.di

import org.koin.dsl.module
import ru.muztache.feature.tuner.data.repository.GuitarTuningRepositoryImpl

val tuningRepositoryModule = module {
    single() { GuitarTuningRepositoryImpl(get()) }
}