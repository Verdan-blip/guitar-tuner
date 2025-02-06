package ru.muztache.feature.chords.impl.ui

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.muztache.core.common.base.viewmodel.BaseViewModel
import ru.muztache.core.common.entity.FetchRequest
import ru.muztache.core.common.provider.ResourceProvider
import ru.muztache.feature.chords.api.domain.usecase.GetBasicChordsUseCase
import ru.muztache.feature.chords.impl.ui.mapper.toChordModelList
import ru.muztache.feature.chords.impl.ui.mvi.Event
import ru.muztache.feature.chords.impl.ui.mvi.State

internal class ChordsViewModel(
    private val getBasicChordsUseCase: GetBasicChordsUseCase,
    resourceProvider: ResourceProvider
) : BaseViewModel<State, Event>(resourceProvider) {

    private val _state = MutableStateFlow(State())
    override val state: StateFlow<State> get() = _state

    override fun reducer(event: Event) {
        when (event) {
            is Event.Load -> onLoad()
        }
    }

    private fun onLoad() {
        viewModelScope.launch {
            _state.emit(_state.value.copy(baseChords = FetchRequest.Pending))
            doSafeCall(onException = ::onLoadException) {
                val basicChords = getBasicChordsUseCase().toChordModelList()
                _state.emit(_state.value.copy(baseChords = FetchRequest.Success(basicChords)))
            }
        }
    }

    private suspend fun onLoadException(ex: Exception) {
        _state.emit(_state.value.copy(baseChords = FetchRequest.Failure(ex)))
    }
}