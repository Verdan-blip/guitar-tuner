package ru.muztache.feature.splash.ui.mvi

import ru.muztache.core.common.base.mvi.BaseState

data class SplashState(
    val isSplashShowing: Boolean
) : BaseState {

    companion object {

        fun create(): SplashState = SplashState(
            isSplashShowing = true
        )
    }
}