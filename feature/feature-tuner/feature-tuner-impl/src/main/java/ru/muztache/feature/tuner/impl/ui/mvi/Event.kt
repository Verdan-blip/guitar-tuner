package ru.muztache.feature.tuner.impl.ui.mvi

import ru.muztache.core.common.base.mvi.BaseEvent

internal sealed interface Event : BaseEvent {

    data class StringSelect(val stringNumber: Int) : Event

    data object AutoDetectSwitch : Event

    data object Load : Event

    data object ScreenEntered: Event

    data object ScreenExited : Event
}
