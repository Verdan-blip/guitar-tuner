package ru.muztache.feature.chords.impl.ui.screens.library

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.muztache.core.common.base.mvi.BaseEffect
import ru.muztache.core.common.base.viewmodel.BaseViewModel
import ru.muztache.core.common.provider.ResourceProvider
import ru.muztache.feature.chords.impl.ui.mvi.ChordsLibraryEvent
import ru.muztache.feature.chords.impl.ui.mvi.State
import ru.muztache.feature.chords.impl.ui.navigation.InnerRoute

internal class ChordsViewModel(
    resourceProvider: ResourceProvider
) : BaseViewModel<State, ChordsLibraryEvent>(resourceProvider) {

    private val _state = MutableStateFlow(State())
    override val state: StateFlow<State> get() = _state

    override fun reducer(event: ChordsLibraryEvent) {
        when (event) {
            is ChordsLibraryEvent.AdvancedChordsClick -> onAdvancedChordsClick()
            is ChordsLibraryEvent.BaseChordsClick -> onBasicChordsClick()
        }
    }

    private fun onBasicChordsClick() {
        viewModelScope.launch {
            emitBaseEffect(BaseEffect.NavigateTo(InnerRoute.BaseChordsDetails))
        }
    }

    private fun onAdvancedChordsClick() {
        viewModelScope.launch {
            emitBaseEffect(BaseEffect.NavigateTo(InnerRoute.AdvancedChordsDetails))
        }
    }
}
