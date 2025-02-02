package ru.muztache.feature.tuner.impl.ui.engine.processor.audio

import be.tarsos.dsp.AudioDispatcher
import be.tarsos.dsp.pitch.PitchDetectionHandler
import be.tarsos.dsp.pitch.PitchProcessor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import ru.muztache.feature.tuner.impl.ui.engine.processor.config.AudioProcessingConfig

typealias TarsosAudioDispatcher = AudioDispatcher

class AudioProcessorImpl(
    private val audioDispatcher: TarsosAudioDispatcher,
    private val applicationScope: CoroutineScope
) : AudioProcessor() {

    private val _frequency = MutableSharedFlow<AudioProcessedData>()
    override val frequency: SharedFlow<AudioProcessedData> get() = _frequency

    private val pitchDetectionHandler = PitchDetectionHandler { result, event ->
        applicationScope.launch(Dispatchers.Default) {
            _frequency.emit(AudioProcessedData(
                pitch = result.pitch,
                isPitched = result.isPitched,
                isSilence = event.isSilence(AudioProcessingConfig.SILENCE_THRESHOLD),
                probability = result.probability,
                dBSPL = event.getdBSPL()
            ))
        }
    }

    private val pitchProcessor = PitchProcessor(
        PitchProcessor.PitchEstimationAlgorithm.FFT_YIN,
        AudioProcessingConfig.SAMPLE_RATE.toFloat(),
        AudioProcessingConfig.BUFFER_SIZE,
        pitchDetectionHandler
    )

    init {
        audioDispatcher.addAudioProcessor(pitchProcessor)
    }

    override suspend fun start() {
        audioDispatcher.run()
    }

    override suspend fun stop() {
        audioDispatcher.stop()
    }
}