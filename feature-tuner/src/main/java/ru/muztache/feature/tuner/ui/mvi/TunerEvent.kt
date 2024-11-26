package ru.muztache.feature.tuner.ui.mvi

import ru.muztache.core.common.base.BaseEvent

sealed interface TunerEvent : BaseEvent {

    data class StringSelect(val stringNumber: Int) : TunerEvent
}