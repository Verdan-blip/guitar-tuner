package ru.muztache.feature.tuner.ui.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import ru.muztache.feature.tuner.ui.TunerViewModel

val featureTunerModule = module {
    includes(
        audioProcessorModule,
        pitchProcessorModule,
        pitchAnalyzerModule
    )
    viewModelOf(::TunerViewModel)
}