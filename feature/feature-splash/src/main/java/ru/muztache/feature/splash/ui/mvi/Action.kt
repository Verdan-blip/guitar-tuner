package ru.muztache.feature.splash.ui.mvi

internal sealed interface Action {

    data object RequestPermissionForRecordAudio : Action

    data class ShowRationaleWithAction(val message: String, val positiveButton: String) : Action

    data class ShowRationale(val message: String) : Action
}
