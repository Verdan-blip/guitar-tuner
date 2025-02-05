package ru.muztache.feature.chords.impl.ui

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.muztache.core.common.base.viewmodel.BaseViewModel
import ru.muztache.core.common.entity.FetchRequest
import ru.muztache.feature.chords.api.domain.usecase.GetBaseGuitarChordsUseCase
import ru.muztache.feature.chords.impl.ui.mapper.toChordModelList
import ru.muztache.feature.chords.impl.ui.mvi.Event
import ru.muztache.feature.chords.impl.ui.mvi.State

internal class ChordsViewModel(
    private val getBaseGuitarChordsUseCase: GetBaseGuitarChordsUseCase
) : BaseViewModel<State, Event>() {

    private val _state = MutableStateFlow(State())
    override val state: StateFlow<State> get() = _state

    override fun reducer(event: Event) {
        when (event) {
            is Event.Load -> onLoad()
        }
    }

    private fun onLoad() {
        viewModelScope.launch {
            val baseChords = doSafeCall(
                onException = { ex ->
                    FetchRequest.Failure(ex)
                },
                body = {
                    FetchRequest.Success(getBaseGuitarChordsUseCase().toChordModelList())
                }
            )
            _state.emit(_state.value.copy(baseChords = baseChords))
        }
    }
}