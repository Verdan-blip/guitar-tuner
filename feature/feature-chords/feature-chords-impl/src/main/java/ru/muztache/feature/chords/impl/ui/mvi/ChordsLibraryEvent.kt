package ru.muztache.feature.chords.impl.ui.mvi

import ru.muztache.core.common.base.mvi.BaseEvent

internal sealed interface ChordsLibraryEvent : BaseEvent {

    data object BaseChordsClick : ChordsLibraryEvent

    data object AdvancedChordsClick : ChordsLibraryEvent
}
