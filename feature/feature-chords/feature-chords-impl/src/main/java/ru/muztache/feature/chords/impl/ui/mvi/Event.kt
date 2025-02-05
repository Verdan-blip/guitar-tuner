package ru.muztache.feature.chords.impl.ui.mvi

import ru.muztache.core.common.base.mvi.BaseEvent

internal sealed interface Event : BaseEvent {

    data object Load : Event
}
