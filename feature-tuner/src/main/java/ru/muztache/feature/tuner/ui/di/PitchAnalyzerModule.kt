package ru.muztache.feature.tuner.ui.di

import org.koin.dsl.module
import ru.muztache.feature.tuner.ui.engine.analyzer.PitchAnalyzer
import ru.muztache.feature.tuner.ui.engine.analyzer.PitchAnalyzerImpl

val pitchAnalyzerModule = module {
    single<PitchAnalyzer> { PitchAnalyzerImpl() }
}