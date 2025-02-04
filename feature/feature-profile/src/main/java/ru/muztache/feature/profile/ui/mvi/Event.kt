package ru.muztache.feature.profile.ui.mvi

import ru.muztache.core.common.base.mvi.BaseEvent

internal sealed interface Event : BaseEvent {

    data object RegisterClick : Event

    data object LogInClick : Event
}