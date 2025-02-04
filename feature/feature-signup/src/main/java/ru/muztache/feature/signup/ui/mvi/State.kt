package ru.muztache.feature.signup.ui.mvi

import ru.muztache.core.common.base.mvi.BaseState
import ru.muztache.core.common.entity.TextFieldState

internal data class State(
    val email: TextFieldState = TextFieldState.Idle(""),
    val password: TextFieldState = TextFieldState.Idle(""),
    val confirmedPassword: TextFieldState = TextFieldState.Idle("")
) : BaseState