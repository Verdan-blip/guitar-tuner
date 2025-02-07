package ru.muztache.feature.profile.ui

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.muztache.core.common.base.mvi.BaseEffect
import ru.muztache.core.common.base.viewmodel.BaseViewModel
import ru.muztache.core.common.entity.TextFieldState
import ru.muztache.core.common.provider.ResourceProvider
import ru.muztache.core.data.local.user.exceptions.AuthException
import ru.muztache.feature.profile.domain.usecase.ChangeUserNameUseCase
import ru.muztache.feature.profile.domain.usecase.GetUserProfileUseCase
import ru.muztache.feature.profile.ui.entity.AuthResult
import ru.muztache.feature.profile.ui.entity.UserProfileModel
import ru.muztache.feature.profile.ui.mapper.toUserProfileModel
import ru.muztache.feature.profile.ui.mvi.Event
import ru.muztache.feature.profile.ui.mvi.State
import ru.muztache.feature.profile.ui.route.ProfileRoute

internal class ProfileViewModel(
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val changeUserNameUseCase: ChangeUserNameUseCase,
    resourceProvider: ResourceProvider
) : BaseViewModel<State, Event>(resourceProvider) {

    private val _state = MutableStateFlow(State())
    override val state: StateFlow<State> get() = _state

    private var userProfileScreenshot: UserProfileModel? = null

    override fun reducer(event: Event) {
        when (event) {
            is Event.Load -> onLoad()
            is Event.SignInClick -> onSignInClick()
            is Event.SignUpClick -> onSignUpClick()
            is Event.NameChange -> onNameChange(event.name)
            is Event.NameSubmit -> onNameChangeSubmit()
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

    private fun onNameChange(name: String) {
        viewModelScope.launch {
            userProfileScreenshot?.also { profile ->
                val changed = profile.copy(name = TextFieldState.Idle(name))
                _state.emit(_state.value.copy(
                    authResult = AuthResult.Authorized(userProfile = changed)
                ))
                userProfileScreenshot = changed
            }
        }
    }

    private fun onNameChangeSubmit() {
        viewModelScope.launch {
            doSafeCall {
                userProfileScreenshot?.also { profile ->
                    changeUserNameUseCase(profile.name.value)
                }
            }
        }
    }

    private fun onLoad() {
        viewModelScope.launch {
            doSafeCall(onException = ::onLoadException) {
                _state.emit(_state.value.copy(authResult = AuthResult.Pending))
                val userProfile = getUserProfileUseCase().toUserProfileModel()
                _state.emit(_state.value.copy(authResult = AuthResult.Authorized(userProfile)))
                userProfileScreenshot = userProfile
            }
        }
    }

    private suspend fun onLoadException(ex: Exception) {
        when (ex) {
            is AuthException.Unauthorized -> {
                _state.emit(_state.value.copy(authResult = AuthResult.UnAuthorized))
            }
            else -> {
                emitBaseEffect(BaseEffect.ShowSnackBar(getProvidedString(ru.muztache.core.common.R.string.unknown_error)))
            }
        }
    }
}