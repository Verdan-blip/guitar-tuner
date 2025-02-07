package ru.muztache.feature.splash.ui

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.muztache.core.common.base.mvi.BaseEffect
import ru.muztache.core.common.base.viewmodel.BaseViewModel
import ru.muztache.core.common.provider.ResourceProvider
import ru.muztache.feature.chords.api.FeatureChordsApi
import ru.muztache.feature.chords.api.domain.entity.Chord
import ru.muztache.feature.splash.R
import ru.muztache.feature.splash.ui.mvi.Action
import ru.muztache.feature.splash.ui.mvi.Event
import ru.muztache.feature.splash.ui.mvi.State
import ru.muztache.feature.splash.ui.route.SplashScreenRoute
import ru.muztache.feature.tuner.api.FeatureTunerApi
import ru.muztache.feature.tuner.api.domain.entity.instrument.Guitar
import ru.muztache.feature.tuner.api.domain.entity.instrument.Ukulele

internal class SplashViewModel(
    private val tunerApi: FeatureTunerApi,
    private val chordsApi: FeatureChordsApi,
    resourceProvider: ResourceProvider
) : BaseViewModel<State, Event>(resourceProvider) {

    private val _state = MutableStateFlow(State.create())
    override val state: StateFlow<State> get() = _state

    private val _action = MutableSharedFlow<Action>(replay = 1)
    val action: SharedFlow<Action> get() = _action

    override fun reducer(event: Event) {
        when (event) {
            is Event.Launch -> { onScreenLaunched() }
            is Event.PermissionDenied -> onPermissionDenied()
            is Event.PermissionGranted -> onPermissionGranted()
            is Event.ReRequestPermission -> onReRequestPermission()
            is Event.PermissionDeniedUnquestionable -> onPermissionDeniedUnquestionable()
        }
    }

    private fun onScreenLaunched() {
        viewModelScope.launch {
            _action.emit(Action.RequestPermissionForRecordAudio)
        }
    }

    private fun onReRequestPermission() {
        viewModelScope.launch {
            _action.emit(Action.RequestPermissionForRecordAudio)
        }
    }

    private fun onPermissionGranted() {
        viewModelScope.launch {
            doSafeCall {
                val syncJob = launch {
                    launch { saveGuitars(tunerApi.getReservedGuitarsUseCase()) }
                    launch { saveUkuleles(tunerApi.getReservedUkulelesUseCase()) }
                    launch { saveGuitarChords(chordsApi.getReservedGuitarChordsUseCase()) }
                }
                syncJob.invokeOnCompletion {
                    launch { emitBaseEffect(BaseEffect.NavigateTo(SplashScreenRoute.HomeScreen)) }
                }
            }
        }
    }

    private fun onPermissionDenied() {
        viewModelScope.launch {
            _action.emit(Action.ShowRationaleWithAction(
                getProvidedString(R.string.we_need_permission_to_microphone),
                getProvidedString(R.string.give_permission)
            ))
        }
    }

    private fun onPermissionDeniedUnquestionable() {
        viewModelScope.launch {
            _action.emit(Action.ShowRationale(
                getProvidedString(R.string.provide_permission_via_settings)
            ))
        }
    }

    private suspend fun saveGuitars(guitars: Map<String, Guitar>) {
        guitars.forEach { (name, guitar) ->
            tunerApi.saveGuitarUseCase(name, guitar)
        }
    }

    private suspend fun saveUkuleles(ukuleles: Map<String, Ukulele>) {
        ukuleles.forEach { (name, ukulele) ->
            tunerApi.saveUkuleleUseCase(name, ukulele)
        }
    }

    private suspend fun saveGuitarChords(chords: List<Chord>) {
        chordsApi.saveGuitarChordUseCase(chords)
    }
}
