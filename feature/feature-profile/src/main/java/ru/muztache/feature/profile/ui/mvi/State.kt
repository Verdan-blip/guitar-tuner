package ru.muztache.feature.profile.ui.mvi

import ru.muztache.core.common.base.mvi.BaseState
import ru.muztache.feature.profile.ui.entity.AuthResult

internal data class State(
    val authResult: AuthResult = AuthResult.Pending
) : BaseState