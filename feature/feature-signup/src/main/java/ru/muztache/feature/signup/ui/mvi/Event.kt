package ru.muztache.feature.signup.ui.mvi

import ru.muztache.core.common.base.mvi.BaseEvent

internal sealed interface Event : BaseEvent {

    data object OnSubmit : Event

    data object OnHaveAnAccountClick : Event

    data class EmailChange(val email: String) : Event

    data class PasswordChange(val password: String) : Event

    data class ConfirmedPasswordChange(val confirmedPassword: String) : Event
}