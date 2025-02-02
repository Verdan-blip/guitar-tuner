package ru.muztache.feature.tuner.impl.ui.mvi

import ru.muztache.core.common.base.mvi.BaseEvent

sealed interface TunerEvent : BaseEvent {

    data class StringSelect(val stringNumber: Int) : TunerEvent

    data object AutoDetectSwitch : TunerEvent
}