package ru.muztache.feature.tuner.impl.ui.di

import org.koin.dsl.module
import ru.muztache.feature.tuner.impl.ui.engine.processor.pitch.FrequencyProcessor
import ru.muztache.feature.tuner.impl.ui.engine.processor.pitch.FrequencyProcessorImpl

internal val frequencyProcessorModule = module {
    single<FrequencyProcessor> { FrequencyProcessorImpl(get(), get()) }
}
