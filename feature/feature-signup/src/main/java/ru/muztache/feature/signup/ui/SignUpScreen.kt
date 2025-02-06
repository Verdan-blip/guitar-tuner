package ru.muztache.feature.signup.ui

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
import ru.muztache.core.common.entity.TextFieldState
import ru.muztache.core.theme.MuztacheTheme
import ru.muztache.core.theme.composable.button.MuztacheTextButton
import ru.muztache.core.theme.composable.field.MuztacheTextField
import ru.muztache.core.theme.snackbar.LocalMuztacheSnackBar
import ru.muztache.feature.signup.R
import ru.muztache.feature.signup.ui.mvi.Event
import ru.muztache.feature.signup.ui.mvi.State
import ru.muztache.feature.signup.ui.route.SignUpRoute

@Composable
fun SignUpScreen(
    onNavigateToProfile: () -> Unit,
    onNavigateToSignIn: () -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel = koinViewModel<SignUpViewModel>()
    val state = viewModel.state.collectAsStateWithLifecycle()
    val effect = viewModel.effect.collectAsStateWithLifecycle(null)

    SignUpScreenContent(
        state = state.value,
        onEvent = { event -> viewModel.reducer(event) },
        modifier = modifier
    )

    val snackBarHost = LocalMuztacheSnackBar.current
    LaunchedEffect(effect.value) {
        when (val value = effect.value) {
            is BaseEffect.NavigateTo -> {
                when (value.route) {
                    is SignUpRoute.Profile -> {
                        onNavigateToProfile()
                    }
                    is SignUpRoute.SignIn -> {
                        onNavigateToSignIn()
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
private fun SignUpScreenContent(
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
                text = stringResource(R.string.title_signup),
                style = MuztacheTheme.typography.displayLarge,
                color = MuztacheTheme.colors.textPrimary
            )
            Row(
                modifier = Modifier
                    .padding(top = MuztacheTheme.paddings.large)
            ) {
                Text(
                    text = stringResource(R.string.if_you_have_an_account),
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
                        .clickable { onEvent(Event.OnHaveAnAccountClick) }
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
            MuztacheTextField(
                textFieldState = state.confirmedPassword,
                onValueChange = { value -> onEvent(Event.ConfirmedPasswordChange(value)) },
                placeholder = stringResource(R.string.hint_confirmed_password),
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = MuztacheTheme.paddings.medium)
            )
            MuztacheTextButton(
                onClick = { onEvent(Event.OnSubmit) },
                text = stringResource(R.string.create_account),
                modifier = Modifier
                    .padding(top = MuztacheTheme.paddings.large)
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun SignUpScreenPreview() {
    MuztacheTheme {
        SignUpScreenContent(
            state = State(
                email = TextFieldState.Error("sample", "Adsjfsdkjfjsdjkfdkjh kvdkfjdkj djfdkfjdkj dfkdjkf")
            ),
            onEvent = { },
            modifier = Modifier
                .fillMaxSize()
        )
    }
}