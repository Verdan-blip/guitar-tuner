package ru.muztache.feature.tuner.impl.ui.di

import be.tarsos.dsp.io.android.AudioDispatcherFactory
import org.koin.dsl.module
import ru.muztache.feature.tuner.impl.ui.engine.processor.audio.AudioProcessor
import ru.muztache.feature.tuner.impl.ui.engine.processor.audio.AudioProcessorImpl
import ru.muztache.feature.tuner.impl.ui.engine.processor.config.AudioProcessingConfig

internal val audioProcessorModule = module {
    single {
        {
            AudioDispatcherFactory.fromDefaultMicrophone(
                AudioProcessingConfig.SAMPLE_RATE, AudioProcessingConfig.BUFFER_SIZE, 0
            )
        }
    }
    single<AudioProcessor> { AudioProcessorImpl(get(), get()) }
}
