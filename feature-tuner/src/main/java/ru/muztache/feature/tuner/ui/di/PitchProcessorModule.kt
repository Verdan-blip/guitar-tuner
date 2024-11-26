package ru.muztache.feature.tuner.ui.di

import org.koin.dsl.module
import ru.muztache.feature.tuner.ui.engine.processor.pitch.PitchProcessor
import ru.muztache.feature.tuner.ui.engine.processor.pitch.PitchProcessorImpl

internal val pitchProcessorModule = module {
    single<PitchProcessor> { PitchProcessorImpl(get(), get()) }
}