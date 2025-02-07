package ru.muztache.feature.tuner.impl.ui.engine.processor.pitch

import kotlinx.coroutines.flow.SharedFlow
import ru.muztache.feature.tuner.impl.ui.engine.processor.Processor

interface FrequencyProcessor : Processor {

    val frequency: SharedFlow<Float>
}
