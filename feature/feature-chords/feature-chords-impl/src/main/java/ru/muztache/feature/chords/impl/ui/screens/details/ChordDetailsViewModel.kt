package ru.muztache.feature.chords.impl.ui.screens.details

import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.muztache.core.common.base.viewmodel.BaseViewModel
import ru.muztache.core.common.entity.FetchRequest
import ru.muztache.core.common.provider.ResourceProvider
import ru.muztache.feature.chords.api.domain.usecase.GetAdvancedChordsUseCase
import ru.muztache.feature.chords.api.domain.usecase.GetBasicChordsUseCase
import ru.muztache.feature.chords.impl.ui.mapper.toChordModelList
import ru.muztache.feature.chords.impl.ui.screens.details.mvi.ChordDetailsEvent
import ru.muztache.feature.chords.impl.ui.screens.details.mvi.ChordDetailsState

internal class ChordDetailsViewModel(
    private val getBasicChordsUseCase: GetBasicChordsUseCase,
    private val getAdvancedChordsUseCase: GetAdvancedChordsUseCase,
    resourceProvider: ResourceProvider
) : BaseViewModel<ChordDetailsState, ChordDetailsEvent>(resourceProvider) {

    private val _state = MutableStateFlow(ChordDetailsState())
    override val state: StateFlow<ChordDetailsState> get() = _state

    override fun reducer(event: ChordDetailsEvent) {
        when (event) {
            ChordDetailsEvent.LoadAdvancedChords -> onLoadAdvancedChords()
            ChordDetailsEvent.LoadBasicChords -> onLoadBasicChords()
        }
    }

    private fun onLoadBasicChords() {
        viewModelScope.launch {
            _state.emit(_state.value.copy(chords = FetchRequest.Pending))
            doSafeCall(onException = ::onLoadException) {
                val chords = getBasicChordsUseCase().toChordModelList()
                _state.emit(_state.value.copy(chords = FetchRequest.Success(chords)))
            }
        }
    }

    private fun onLoadAdvancedChords() {
        viewModelScope.launch {
            _state.emit(_state.value.copy(chords = FetchRequest.Pending))
            doSafeCall(onException = ::onLoadException) {
                val chords = getAdvancedChordsUseCase().toChordModelList()
                _state.emit(_state.value.copy(chords = FetchRequest.Success(chords)))
            }
        }
    }

    private suspend fun onLoadException(ex: Exception) {
        _state.emit(_state.value.copy(chords = FetchRequest.Failure(ex)))
    }
}
