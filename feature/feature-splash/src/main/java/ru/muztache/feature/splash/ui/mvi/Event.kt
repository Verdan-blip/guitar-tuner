package ru.muztache.feature.splash.ui.mvi

import ru.muztache.core.common.base.mvi.BaseEvent

internal sealed interface Event : BaseEvent {

    data object Launch : Event

    data object PermissionGranted : Event

    data object PermissionDenied : Event

    data object PermissionDeniedUnquestionable : Event

    data object ReRequestPermission : Event
}
