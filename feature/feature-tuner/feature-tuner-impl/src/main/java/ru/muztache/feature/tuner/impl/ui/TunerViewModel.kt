package ru.muztache.feature.tuner.impl.ui

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.muztache.core.common.base.viewmodel.BaseViewModel
import ru.muztache.core.common.provider.ResourceProvider
import ru.muztache.feature.tuner.impl.ui.engine.analyzer.AnalyzeResult
import ru.muztache.feature.tuner.impl.ui.engine.analyzer.FrequencyAnalyzer
import ru.muztache.feature.tuner.impl.ui.engine.processor.pitch.FrequencyProcessor
import ru.muztache.feature.tuner.impl.ui.mvi.Action
import ru.muztache.feature.tuner.impl.ui.mvi.Event
import ru.muztache.feature.tuner.impl.ui.mvi.State

internal class TunerViewModel(
    private val frequencyProcessor: FrequencyProcessor,
    private val frequencyAnalyzer: FrequencyAnalyzer,
    resourceProvider: ResourceProvider
) : BaseViewModel<State, Event>(resourceProvider) {

    private val _state = MutableStateFlow(State.create())
    override val state: StateFlow<State> get() = _state

    private val _action = MutableSharedFlow<Action>()
    val action: SharedFlow<Action> get() = _action

    init {
        viewModelScope.launch(Dispatchers.Default) {
            collectPitches()
        }
    }

    override fun reducer(event: Event) {
        when (event) {
            is Event.StringSelect -> onStringSelect(event.stringNumber)
            is Event.AutoDetectSwitch -> onAutodetectSwitch()
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

    private fun onStringSelect(stringNumber: Int) {
        val instrument = _state.value.selectedInstrument
        _state.value = _state.value.copy(
            selectedString = stringNumber,
            idolNote = instrument.getToneWithOctave(stringNumber)
        )
    }

    private fun onAutodetectSwitch() {
        _state.value = _state.value.copy(
            isAutoDetect = _state.value.isAutoDetect.not()
        )
    }

    private suspend fun collectPitches() {
        frequencyProcessor.frequency.collect { frequency ->
            val selectedString = _state.value.selectedString
            val toneForString = _state.value.selectedInstrument.getToneWithOctave(selectedString)
            val analyzeResult = if (_state.value.isAutoDetect) {
                frequencyAnalyzer.analyze(frequency, _state.value.selectedInstrument.tuning)
            } else {
                frequencyAnalyzer.analyzeComparing(frequency, toneForString)
            }
            when (analyzeResult) {
                is AnalyzeResult.Success -> {
                    _action.emit(Action.OnNewDeviation(deviation = analyzeResult.deviation))
                    _state.emit(_state.value.copy(
                        isTuned = analyzeResult.isTuned,
                        currentFrequency = analyzeResult.frequency,
                        idolNote = analyzeResult.nearestTone
                    ))
                }
                is AnalyzeResult.Failure -> {  }
            }
        }
    }
}
