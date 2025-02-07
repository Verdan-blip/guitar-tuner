package ru.muztache.feature.signin.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import ru.muztache.core.common.base.mvi.BaseEffect
import ru.muztache.core.theme.MuztacheTheme
import ru.muztache.core.theme.composable.button.MuztacheTextButton
import ru.muztache.core.theme.composable.field.MuztacheTextField
import ru.muztache.core.theme.snackbar.LocalMuztacheSnackBar
import ru.muztache.feature.signin.R
import ru.muztache.feature.signin.ui.mvi.Event
import ru.muztache.feature.signin.ui.mvi.State
import ru.muztache.feature.signin.ui.route.SignInRoute

@Composable
fun SignInScreen(
    onNavigateToProfile: () -> Unit,
    onNavigateToSignUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel = koinViewModel<SignInViewModel>()
    val state = viewModel.state.collectAsStateWithLifecycle()
    val effect = viewModel.effect.collectAsStateWithLifecycle(null)

    SignInScreenContent(
        state = state.value,
        onEvent = { event -> viewModel.reducer(event) },
        modifier = modifier
    )

    val snackBarState = LocalMuztacheSnackBar.current
    LaunchedEffect(effect.value) {
        when (val value = effect.value) {
            is BaseEffect.NavigateTo -> {
                when (value.route) {
                    is SignInRoute.Profile -> {
                        onNavigateToProfile()
                    }
                    is SignInRoute.SignUp -> {
                        onNavigateToSignUp()
                    }
                }
            }
            is BaseEffect.ShowSnackBar -> {
                snackBarState.showSnackbar(value.message)
            }
            else -> Unit
        }
    }
}

@Composable
private fun SignInScreenContent(
    state: State,
    onEvent: (Event) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = MuztacheTheme.paddings.extraLarge),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.title_signin),
                style = MuztacheTheme.typography.displayLarge,
                color = MuztacheTheme.colors.textPrimary
            )
            Row(
                modifier = Modifier
                    .padding(top = MuztacheTheme.paddings.large)
            ) {
                Text(
                    text = stringResource(R.string.if_you_already_have_an_account),
                    style = MuztacheTheme.typography.bodySmall,
                    color = MuztacheTheme.colors.textPrimary
                )
                Text(
                    text = stringResource(R.string.click_here),
                    style = MuztacheTheme.typography.bodySmall,
                    color = MuztacheTheme.colors.primary,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = MuztacheTheme.paddings.small)
                        .clickable { onEvent(Event.DoNotHaveAnAccountClick) }
                )
            }
            MuztacheTextField(
                textFieldState = state.email,
                onValueChange = { value -> onEvent(Event.EmailChange(value)) },
                placeholder = stringResource(R.string.hint_email),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = MuztacheTheme.paddings.extraLarge)
            )
            MuztacheTextField(
                textFieldState = state.password,
                onValueChange = { value -> onEvent(Event.PasswordChange(value)) },
                placeholder = stringResource(R.string.hint_password),
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = MuztacheTheme.paddings.medium)
            )
            MuztacheTextButton(
                onClick = { onEvent(Event.Submit) },
                text = stringResource(R.string.signin),
                modifier = Modifier
                    .padding(top = MuztacheTheme.paddings.medium)
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun SignInScreenPreview() {
    MuztacheTheme {
        SignInScreenContent(
            state = State(),
            onEvent = { },
            modifier = Modifier
                .fillMaxSize()
        )
    }
}