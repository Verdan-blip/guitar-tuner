package ru.muztache.feature.tuner.impl.ui.engine.processor.pitch

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.muztache.core.common.flow.bufferByTime
import ru.muztache.feature.tuner.impl.ui.engine.processor.audio.AudioProcessor
import kotlin.time.Duration.Companion.milliseconds

class FrequencyProcessorImpl(
    private val audioProcessor: AudioProcessor,
    applicationScope: CoroutineScope
) : FrequencyProcessor {

    private val _pitch = MutableSharedFlow<Float>()
    override val frequency: SharedFlow<Float> get() = _pitch

    init {
        applicationScope.launch(Dispatchers.Default) { collectPitches() }
    }

    override suspend fun start() {
        audioProcessor.start()
    }

    override suspend fun stop() {
        audioProcessor.stop()
    }

    private suspend fun collectPitches() {
        audioProcessor.frequency
            .filter { it.pitch > 1f }
            .filter { it.isSilence.not() }
            .bufferByTime(250.milliseconds)
            .filter { it.isNotEmpty() }
            .map { it.groupBy { result -> result.pitch.toInt() } }
            .map { it.maxBy { result -> result.value.size } }
            .map { it.value.maxBy { result -> result.probability } }
            .map { it.pitch }
            .collect { pitch -> _pitch.emit(pitch) }
    }
}
