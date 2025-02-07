package ru.muztache.feature.chords.impl.ui.screens.details.mvi

import ru.muztache.core.common.base.mvi.BaseEvent

sealed interface ChordDetailsEvent : BaseEvent {

    data object LoadBasicChords : ChordDetailsEvent

    data object LoadAdvancedChords : ChordDetailsEvent
}
