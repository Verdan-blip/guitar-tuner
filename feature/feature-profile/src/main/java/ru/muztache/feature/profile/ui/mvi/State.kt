package ru.muztache.feature.profile.ui.mvi

import ru.muztache.core.common.base.mvi.BaseState

internal class State(
    val email: String = ""
) : BaseState