package ru.muztache.feature.signin.ui.mvi

import ru.muztache.core.common.base.mvi.BaseEvent

internal sealed interface Event : BaseEvent {

    data object Submit : Event

    data object DoNotHaveAnAccountClick : Event

    data class EmailChange(val email: String) : Event

    data class PasswordChange(val password: String) : Event
}