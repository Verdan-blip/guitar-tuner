package ru.muztache.feature.tuner.impl.ui

import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.muztache.core.common.base.mvi.BaseEffect
import ru.muztache.core.common.base.viewmodel.BaseViewModel
import ru.muztache.core.common.entity.FetchRequest
import ru.muztache.core.common.provider.ResourceProvider
import ru.muztache.feature.tuner.api.domain.entity.instrument.Guitar
import ru.muztache.feature.tuner.api.domain.entity.instrument.StringInstrument
import ru.muztache.feature.tuner.api.domain.usecase.get.GetTunedInstrumentsUseCase
import ru.muztache.feature.tuner.impl.R
import ru.muztache.feature.tuner.impl.ui.engine.analyzer.AnalyzeResult
import ru.muztache.feature.tuner.impl.ui.engine.analyzer.FrequencyAnalyzer
import ru.muztache.feature.tuner.impl.ui.engine.processor.pitch.FrequencyProcessor
import ru.muztache.feature.tuner.impl.ui.mvi.Action
import ru.muztache.feature.tuner.impl.ui.mvi.Event
import ru.muztache.feature.tuner.impl.ui.mvi.State

internal class TunerViewModel(
    private val frequencyProcessor: FrequencyProcessor,
    private val frequencyAnalyzer: FrequencyAnalyzer,
    private val getTunedGuitarsUseCase: GetTunedInstrumentsUseCase<Guitar>,
    resourceProvider: ResourceProvider
) : BaseViewModel<State, Event>(resourceProvider) {

    private val _state = MutableStateFlow(State())
    override val state: StateFlow<State> get() = _state

    private val _action = MutableSharedFlow<Action>()
    val action: SharedFlow<Action> get() = _action

    private var currentInstrument: StringInstrument<*>? = null

    override fun reducer(event: Event) {
        when (event) {
            is Event.StringSelect -> onStringSelect(event.stringNumber)
            is Event.AutoDetectSwitch -> onAutodetectSwitch()
            is Event.ScreenEntered -> onScreenEntered()
            is Event.ScreenExited -> onScreenExited()
            is Event.Load -> onLoad()
            is Event.SelectInstrument -> onInstrumentSelect(event.key)
        }
    }

    private fun onScreenEntered() {
        viewModelScope.launch(Dispatchers.Default) {
            frequencyProcessor.start()
        }
    }

    private fun onLoad() {
        viewModelScope.launch {
            launch { collectPitches() }
            launch { collectGuitarTuningChanges() }
        }
    }

    private fun onScreenExited() {
        viewModelScope.launch(Dispatchers.Default) {
            frequencyProcessor.stop()
        }
    }

    private fun onStringSelect(stringNumber: Int) {
        currentInstrument?.also { instrument ->
            Log.d("ERROR", instrument.getToneWithOctave(stringNumber).frequency.toString())
            _state.value = _state.value.copy(
                selectedString = stringNumber,
                idolNote = instrument.getToneWithOctave(stringNumber)
            )
        }
    }

    private fun onInstrumentSelect(key: String) {
        viewModelScope.launch {
            _state.emit(_state.value.copy(selectedKey = FetchRequest.Success(key)))
        }
    }

    private fun onAutodetectSwitch() {
        _state.value = _state.value.copy(
            isAutoDetect = _state.value.isAutoDetect.not()
        )
    }

    private suspend fun collectGuitarTuningChanges() {
        doSafeCall(onException = { onGetGuitarsException() }) {
            getTunedGuitarsUseCase().collect { guitars ->
                val selectedKey = guitars.keys.first()
                guitars[selectedKey]?.also { guitar ->
                    _state.emit(_state.value.copy(
                        savedTunings = guitars,
                        selectedKey = FetchRequest.Success(selectedKey)
                    ))
                    currentInstrument = guitar
                }
            }
        }
    }

    private suspend fun onGetGuitarsException() {
        emitBaseEffect(
            BaseEffect.ShowSnackBar(getProvidedString(R.string.failed_to_fetch_guitars))
        )
    }

    private suspend fun collectPitches() {
        frequencyProcessor.frequency.collect { frequency ->
            _state.value.apply {
                currentInstrument?.also { instrument ->
                    val selectedString = selectedString
                    val toneForString = instrument.getToneWithOctave(selectedString)
                    val analyzeResult = if (isAutoDetect) {
                        frequencyAnalyzer.analyze(frequency, instrument.tuning)
                    } else {
                        frequencyAnalyzer.analyzeComparing(frequency, toneForString)
                    }
                    when (analyzeResult) {
                        is AnalyzeResult.Success -> {
                            _action.emit(Action.OnNewDeviation(deviation = analyzeResult.deviation))
                            _state.emit(copy(
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
    }
}
