package ru.muztache.feature.profile.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import ru.muztache.core.common.base.mvi.BaseEffect
import ru.muztache.core.theme.MuztacheTheme
import ru.muztache.core.theme.composable.progress.MuztacheProgressIndicator
import ru.muztache.core.theme.composable.surface.MuztacheSurface
import ru.muztache.core.theme.snackbar.LocalMuztacheSnackBar
import ru.muztache.feature.profile.ui.composable.AuthorizedScreen
import ru.muztache.feature.profile.ui.composable.UnauthorizedScreen
import ru.muztache.feature.profile.ui.entity.AuthResult
import ru.muztache.feature.profile.ui.mvi.Event
import ru.muztache.feature.profile.ui.mvi.State
import ru.muztache.feature.profile.ui.route.ProfileRoute

@Composable
fun ProfileScreen(
    onNavigateToSignUp: () -> Unit,
    onNavigateToSignIn: () -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel = koinViewModel<ProfileViewModel>()
    val state = viewModel.state.collectAsStateWithLifecycle()
    val effect = viewModel.effect.collectAsStateWithLifecycle(null)

    ProfileScreenContent(
        state = state.value,
        onEvent = { event -> viewModel.reducer(event) },
        modifier = modifier
    )

    LaunchedEffect(Unit) {
        viewModel.reducer(Event.Load)
    }

    val snackBarHost = LocalMuztacheSnackBar.current
    LaunchedEffect(effect.value) {
        when (val value = effect.value) {
            is BaseEffect.NavigateTo -> {
                when (value.route) {
                    is ProfileRoute.SignIn -> {
                        onNavigateToSignIn()
                    }
                    is ProfileRoute.SignUp -> {
                        onNavigateToSignUp()
                    }
                }
            }
            is BaseEffect.ShowSnackBar -> {
                snackBarHost.showSnackbar(value.message)
            }
            else -> Unit
        }
    }
}

@Composable
private fun ProfileScreenContent(
    state: State,
    onEvent: (Event) -> Unit,
    modifier: Modifier = Modifier
) {
    when (state.authResult) {
        is AuthResult.Pending -> {
            Box(
                modifier = modifier
            ) {
                MuztacheProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }
        is AuthResult.UnAuthorized -> {
            UnauthorizedScreen(
                onSignUpClick = { onEvent(Event.SignUpClick) },
                onSignInClick = { onEvent(Event.SignInClick) },
                modifier = modifier
            )
        }
        is AuthResult.Authorized -> {
            AuthorizedScreen(
                userProfileEntity = state.authResult.userProfile,
                modifier = modifier
            )
        }
    }
}

@Preview
@Composable
private fun ProfileScreenPreview() {
    MuztacheTheme {
        MuztacheSurface {
            ProfileScreenContent(
                state = State(),
                onEvent = { },
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }
}
