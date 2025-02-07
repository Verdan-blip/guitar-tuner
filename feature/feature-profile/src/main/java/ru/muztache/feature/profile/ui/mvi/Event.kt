package ru.muztache.feature.profile.ui.mvi

import ru.muztache.core.common.base.mvi.BaseEvent

internal sealed interface Event : BaseEvent {

    data object Load : Event

    data object SignUpClick : Event

    data object SignInClick : Event

    data class NameChange(val name: String) : Event

    data object NameSubmit : Event
}