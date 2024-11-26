package ru.muztache.feature.tuner.ui.engine.processor.audio

import kotlinx.coroutines.flow.SharedFlow
import ru.muztache.feature.tuner.ui.engine.processor.Processor

abstract class AudioProcessor : Processor {

    abstract val currentPitch: SharedFlow<AudioProcessedData>
}