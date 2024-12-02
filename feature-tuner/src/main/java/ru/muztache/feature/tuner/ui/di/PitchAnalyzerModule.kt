package ru.muztache.feature.tuner.ui.di

import org.koin.dsl.module
import ru.muztache.feature.tuner.ui.engine.analyzer.FrequencyAnalyzer
import ru.muztache.feature.tuner.ui.engine.analyzer.FrequencyAnalyzerImpl

val frequencyAnalyzerModule = module {
    single<FrequencyAnalyzer> { FrequencyAnalyzerImpl() }
}