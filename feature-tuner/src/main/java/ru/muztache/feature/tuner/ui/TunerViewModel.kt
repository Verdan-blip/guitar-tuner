package ru.muztache.feature.tuner.ui

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.muztache.core.common.base.BaseViewModel
import ru.muztache.feature.tuner.ui.engine.analyzer.AnalyzeResult
import ru.muztache.feature.tuner.ui.engine.analyzer.FrequencyAnalyzer
import ru.muztache.feature.tuner.ui.engine.processor.pitch.FrequencyProcessor
import ru.muztache.feature.tuner.ui.mvi.TunerAction
import ru.muztache.feature.tuner.ui.mvi.TunerEffect
import ru.muztache.feature.tuner.ui.mvi.TunerEvent
import ru.muztache.feature.tuner.ui.mvi.TunerState

class TunerViewModel(
    private val frequencyProcessor: FrequencyProcessor,
    private val frequencyAnalyzer: FrequencyAnalyzer
) : BaseViewModel<TunerState, TunerEvent, TunerEffect>() {

    private val _state = MutableStateFlow(TunerState.create())
    override val state: StateFlow<TunerState> get() = _state

    private val _effect = MutableSharedFlow<TunerEffect>()
    override val effect: SharedFlow<TunerEffect> get() = _effect

    private val _action = MutableSharedFlow<TunerAction>()
    val action: SharedFlow<TunerAction> get() = _action

    init {
        viewModelScope.launch(Dispatchers.Default) {
            collectPitches()
        }
    }

    override fun reducer(event: TunerEvent) {
        when (event) {
            is TunerEvent.StringSelect -> {
                val instrument = _state.value.selectedInstrument
                val stringNumber = event.stringNumber
                _state.value = _state.value.copy(
                    selectedString = stringNumber,
                    idolNote = instrument.getTone(stringNumber)
                )
            }
            is TunerEvent.AutoDetectSwitch -> {
                _state.value = _state.value.copy(
                    isAutoDetect = _state.value.isAutoDetect.not()
                )
            }
        }
    }

    fun onScreenVisible() {
        viewModelScope.launch(Dispatchers.Default) {
            frequencyProcessor.start()
        }
    }

    fun onScreenInvisible() {
        viewModelScope.launch(Dispatchers.Default) {
            frequencyProcessor.stop()
        }
    }

    private suspend fun collectPitches() {
        frequencyProcessor.frequency.collect { frequency ->
            val selectedString = _state.value.selectedString
            val toneForString = _state.value.selectedInstrument.getTone(selectedString)
            val analyzeResult = if (_state.value.isAutoDetect) {
                frequencyAnalyzer.analyze(frequency)
            } else {
                frequencyAnalyzer.analyzeComparing(frequency, toneForString)
            }
            when (analyzeResult) {
                is AnalyzeResult.Success -> {
                    _action.emit(TunerAction.OnNewDeviation(deviation = analyzeResult.deviation))
                    _state.emit(_state.value.copy(
                        isTuned = analyzeResult.isTuned,
                        currentFrequency = analyzeResult.frequency,
                        idolNote = analyzeResult.nearestTone
                    ))
                }
                is AnalyzeResult.Failure -> {

                }
            }
        }
    }
}