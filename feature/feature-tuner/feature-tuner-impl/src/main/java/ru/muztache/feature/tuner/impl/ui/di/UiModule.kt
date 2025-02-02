package ru.muztache.feature.tuner.impl.ui.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import ru.muztache.feature.tuner.impl.ui.TunerViewModel

internal val uiModule = module {
    includes(
        audioProcessorModule,
        frequencyProcessorModule,
        frequencyAnalyzerModule
    )
    viewModelOf(::TunerViewModel)
}