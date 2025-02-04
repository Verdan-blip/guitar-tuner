package ru.muztache.feature.signin.ui

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.muztache.core.common.base.mvi.BaseEffect
import ru.muztache.core.common.base.viewmodel.BaseViewModel
import ru.muztache.core.common.entity.TextFieldState
import ru.muztache.feature.signin.domain.entity.UserForm
import ru.muztache.feature.signin.domain.usecase.SignInUseCase
import ru.muztache.feature.signin.ui.mvi.Event
import ru.muztache.feature.signin.ui.mvi.State
import ru.muztache.feature.signin.ui.route.SignInRoute

internal class SignInViewModel(
    private val signUpUseCase: SignInUseCase
) : BaseViewModel<State, Event>() {

    private val _state = MutableStateFlow(State())
    override val state: StateFlow<State> get() = _state

    override fun reducer(event: Event) {
        when (event) {
            is Event.EmailChange -> onEmailChange(event.email)
            is Event.PasswordChange -> onPasswordChange(event.password)
            is Event.DoNotHaveAnAccountClick -> onDoNotHaveAnAccountClick()
            is Event.Submit -> onSubmit()
        }
    }

    private fun onEmailChange(email: String) {
        viewModelScope.launch {
            _state.emit(_state.value.copy(
                email = TextFieldState.Idle(email)
            ))
        }
    }

    private fun onPasswordChange(password: String) {
        viewModelScope.launch {
            _state.emit(_state.value.copy(
                password = TextFieldState.Idle(password)
            ))
        }
    }

    private fun onSubmit() {
        viewModelScope.launch {
            _state.value.apply {
                signUpUseCase(UserForm(email.value, password.value))
            }
        }
    }

    private fun onDoNotHaveAnAccountClick() {
        viewModelScope.launch {
            emitBaseEffect(BaseEffect.NavigateTo(SignInRoute.SignUp))
        }
    }
}