package ru.muztache.feature.splash.ui

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.muztache.core.common.base.mvi.BaseEffect
import ru.muztache.core.common.base.viewmodel.BaseViewModel
import ru.muztache.core.common.provider.ResourceProvider
import ru.muztache.feature.chords.api.FeatureChordsApi
import ru.muztache.feature.chords.api.domain.entity.Chord
import ru.muztache.feature.splash.ui.mvi.SplashEvent
import ru.muztache.feature.splash.ui.mvi.SplashState
import ru.muztache.feature.splash.ui.route.SplashScreenRoute
import ru.muztache.feature.tuner.api.FeatureTunerApi
import ru.muztache.feature.tuner.api.domain.entity.instrument.Guitar
import ru.muztache.feature.tuner.api.domain.entity.instrument.Ukulele

class SplashViewModel(
    private val tunerApi: FeatureTunerApi,
    private val chordsApi: FeatureChordsApi,
    resourceProvider: ResourceProvider
) : BaseViewModel<SplashState, SplashEvent>(resourceProvider) {

    private val _state = MutableStateFlow(SplashState.create())
    override val state: StateFlow<SplashState> get() = _state

    override fun reducer(event: SplashEvent) {
        when (event) {
            is SplashEvent.Launch -> { onScreenLaunched() }
        }
    }

    private fun onScreenLaunched() {
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