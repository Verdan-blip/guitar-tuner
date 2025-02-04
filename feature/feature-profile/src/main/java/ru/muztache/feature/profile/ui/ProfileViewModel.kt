package ru.muztache.feature.profile.ui

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.muztache.core.common.base.mvi.BaseEffect
import ru.muztache.core.common.base.viewmodel.BaseViewModel
import ru.muztache.feature.profile.ui.mvi.Event
import ru.muztache.feature.profile.ui.mvi.State
import ru.muztache.feature.profile.ui.route.ProfileRoute

internal class ProfileViewModel : BaseViewModel<State, Event>() {

    private val _state = MutableStateFlow(State())
    override val state: StateFlow<State> get() = _state

    override fun reducer(event: Event) {
        when (event) {
            Event.SignInClick -> onSignInClick()
            Event.SignUpClick -> onSignUpClick()
        }
    }

    private fun onSignInClick() {
        viewModelScope.launch {
            emitBaseEffect(BaseEffect.NavigateTo(ProfileRoute.SignIn))
        }
    }

    private fun onSignUpClick() {
        viewModelScope.launch {
            emitBaseEffect(BaseEffect.NavigateTo(ProfileRoute.SignUp))
        }
    }
}