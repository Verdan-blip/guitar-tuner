package ru.muztache.feature.tuner.ui

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.muztache.core.common.base.BaseViewModel
import ru.muztache.feature.tuner.ui.engine.analyzer.PitchAnalyzer
import ru.muztache.feature.tuner.ui.engine.processor.pitch.PitchProcessor
import ru.muztache.feature.tuner.ui.mvi.TunerEffect
import ru.muztache.feature.tuner.ui.mvi.TunerEvent
import ru.muztache.feature.tuner.ui.mvi.TunerState

class TunerViewModel(
    private val pitchProcessor: PitchProcessor,
    private val pitchAnalyzer: PitchAnalyzer
) : BaseViewModel<TunerState, TunerEvent, TunerEffect>() {

    private val _state = MutableStateFlow(TunerState.create())
    override val state: StateFlow<TunerState> get() = _state

    private val _effect = MutableSharedFlow<TunerEffect>()
    override val effect: SharedFlow<TunerEffect> get() = _effect

    init {
        viewModelScope.launch(Dispatchers.Default) { collectPitches() }
    }

    override fun reducer(event: TunerEvent) {
        when (event) {
            is TunerEvent.StringSelect -> {
                val instrument = _state.value.selectedInstrument
                val stringNumber = event.stringNumber
                _state.value = state.value.copy(
                    selectedString = stringNumber,
                    idolNote = instrument.getTone(stringNumber)
                )
            }
        }
    }

    fun onScreenVisible() {
        viewModelScope.launch(Dispatchers.Default) {
            pitchProcessor.start()
        }
    }

    fun onScreenInvisible() {
        viewModelScope.launch(Dispatchers.Default) {
            pitchProcessor.stop()
        }
    }

    private suspend fun collectPitches() {
        pitchProcessor.pitch.collect { pitch ->
            val selectedString = _state.value.selectedString
            val toneForString = _state.value.selectedInstrument.getTone(selectedString)
            val diff = pitchAnalyzer.calculateDiff(pitch, toneForString)
            _state.emit(
                _state.value.copy(
                    currentDiff = diff
                )
            )
        }
    }
}