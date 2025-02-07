package ru.muztache.feature.splash.ui.mvi

import ru.muztache.core.common.base.mvi.BaseState

internal data class State(
    val isSplashShowing: Boolean
) : BaseState {

    companion object {

        fun create(): State = State(
            isSplashShowing = true
        )
    }
}
