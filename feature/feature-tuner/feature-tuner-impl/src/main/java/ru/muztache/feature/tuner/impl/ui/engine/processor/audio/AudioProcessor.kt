package ru.muztache.feature.tuner.impl.ui.engine.processor.audio

import kotlinx.coroutines.flow.SharedFlow
import ru.muztache.feature.tuner.impl.ui.engine.processor.Processor

abstract class AudioProcessor : Processor {

    abstract val frequency: SharedFlow<AudioProcessedData>
}
