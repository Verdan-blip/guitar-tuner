package ru.muztache.feature.tuner.impl.data.di

import org.koin.dsl.module

internal val dataModule = module {
    includes(instrumentRepositoryModule)
}