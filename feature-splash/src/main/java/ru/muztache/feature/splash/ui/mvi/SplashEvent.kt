package ru.muztache.feature.splash.ui.mvi

import ru.muztache.core.common.base.mvi.BaseEvent

sealed interface SplashEvent : BaseEvent {

    data object Launch : SplashEvent
}