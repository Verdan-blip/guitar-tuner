package ru.muztache.feature.signup.ui

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.muztache.core.common.base.mvi.BaseEffect
import ru.muztache.core.common.base.viewmodel.BaseViewModel
import ru.muztache.core.common.entity.TextFieldState
import ru.muztache.core.common.provider.ResourceProvider
import ru.muztache.core.data.local.auth.exceptions.SignUpException
import ru.muztache.feature.signup.R
import ru.muztache.feature.signup.domain.entity.UserForm
import ru.muztache.feature.signup.domain.exceptions.ValidationException
import ru.muztache.feature.signup.domain.interactor.ValidateFormInteractor
import ru.muztache.feature.signup.domain.usecase.SignUpUseCase
import ru.muztache.feature.signup.ui.mvi.Event
import ru.muztache.feature.signup.ui.mvi.State
import ru.muztache.feature.signup.ui.route.SignUpRoute

internal class SignUpViewModel(
    private val signUpUseCase: SignUpUseCase,
    private val validateFormInteractor: ValidateFormInteractor,
    resourceProvider: ResourceProvider
) : BaseViewModel<State, Event>(resourceProvider) {

    private val _state = MutableStateFlow(State())
    override val state: StateFlow<State> get() = _state

    override fun reducer(event: Event) {
        when (event) {
            is Event.ConfirmedPasswordChange -> onConfirmedPasswordChange(event.confirmedPassword)
            is Event.EmailChange -> onEmailChange(event.email)
            is Event.PasswordChange -> onPasswordChange(event.password)
            is Event.OnSubmit -> onSubmit()
            is Event.OnHaveAnAccountClick -> onHaveAnAccountClick()
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

    private fun onConfirmedPasswordChange(confirmedPassword: String) {
        viewModelScope.launch {
            _state.emit(_state.value.copy(
                confirmedPassword = TextFieldState.Idle(confirmedPassword)
            ))
        }
    }

    private fun onSubmit() {
        viewModelScope.launch {
            _state.value.apply {
                doSafeCall(onException = ::onSubmitException) {
                    validateFields(email.value, password.value, confirmedPassword.value)
                    signUpUseCase(UserForm(email.value, password.value))
                    emitBaseEffect(BaseEffect.NavigateTo(SignUpRoute.Profile))
                }
            }
        }
    }

    private fun onHaveAnAccountClick() {
        viewModelScope.launch {
            emitBaseEffect(BaseEffect.NavigateTo(SignUpRoute.SignIn))
        }
    }

    private suspend fun validateFields(
        email: String,
        password: String,
        confirmedPassword: String
    ) {
        validateFormInteractor.apply {
            validateEmail(email)
            validatePassword(password)
            validatePasswordsMatch(password, confirmedPassword)
        }
    }

    private suspend fun onSubmitException(ex: Exception) {
        when (ex) {
            is ValidationException -> onValidationException(ex)
            else -> onSignInException(ex)
        }
    }

    private suspend fun onValidationException(ex: Exception) {
        _state.apply {
            when (ex) {
                is ValidationException.InvalidEmailFormat -> {
                    _state.emit(
                        value.copy(
                            email = TextFieldState.Error(
                                value = value.email.value,
                                message = getProvidedString(R.string.invalid_email_format)
                            )
                        )
                    )
                }
                is ValidationException.InvalidPasswordFormat -> {
                    _state.emit(
                        value.copy(
                            password = TextFieldState.Error(
                                value = "",
                                message = getProvidedString(R.string.insecure_password)
                            )
                        )
                    )
                }
                is ValidationException.PasswordsNotMatch -> {
                    _state.emit(
                        value.copy(
                            password = TextFieldState.Error(
                                value = "",
                                message = getProvidedString(R.string.passwords_does_not_match)
                            )
                        )
                    )
                }
            }
        }
    }

    private suspend fun onSignInException(ex: Exception) {
        val message = when (ex) {
            is SignUpException.SuchUserAlreadyExists -> {
                getProvidedString(R.string.error_such_user_already_exists)
            }
            is SignUpException.FailedToCreateUser -> {
                getProvidedString(R.string.error_failed_to_sign_up)
            }
            else -> {
                getProvidedString(ru.muztache.core.common.R.string.unknown_error)
            }
        }
        emitBaseEffect(BaseEffect.ShowSnackBar(message))
    }
}
