package ru.muztache.feature.splash.ui

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.muztache.core.common.base.mvi.BaseEffect
import ru.muztache.core.common.base.viewmodel.BaseViewModel
import ru.muztache.feature.splash.ui.mvi.SplashEvent
import ru.muztache.feature.splash.ui.mvi.SplashState
import ru.muztache.feature.splash.ui.route.SplashScreenRoute
import ru.muztache.feature.tuner.api.FeatureTunerApi
import ru.muztache.feature.tuner.api.domain.entity.instrument.Guitar
import ru.muztache.feature.tuner.api.domain.entity.instrument.Ukulele

class SplashViewModel(
    private val tunerApi: FeatureTunerApi,
) : BaseViewModel<SplashState, SplashEvent>() {

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
}