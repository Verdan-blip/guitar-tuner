package ru.muztache.feature.profile.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import ru.muztache.core.common.base.mvi.BaseEffect
import ru.muztache.core.theme.MuztacheTheme
import ru.muztache.core.theme.composable.button.MuztacheTextButton
import ru.muztache.core.theme.composable.surface.MuztacheSurface
import ru.muztache.feature.profile.R
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
    Box(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(R.drawable.guitar),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .offset((-120).dp, (64).dp)
                .rotate(-30f)
        )
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = MuztacheTheme.paddings.medium)
        ) {
            Text(
                text = stringResource(R.string.use_all_features_with_account),
                style = MuztacheTheme.typography.displayLarge,
                color = MuztacheTheme.colors.textPrimary,
                textAlign = TextAlign.Center
            )
            Text(
                text = stringResource(R.string.description),
                style = MuztacheTheme.typography.bodyMedium,
                color = MuztacheTheme.colors.textSecondary,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = MuztacheTheme.paddings.large)
            )
            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = MuztacheTheme.paddings.extraLarge)
                    .padding(horizontal = MuztacheTheme.paddings.normal),
                horizontalArrangement = Arrangement
                    .spacedBy(MuztacheTheme.paddings.large)
            ) {
                MuztacheTextButton(
                    text = stringResource(R.string.register),
                    onClick = { onEvent(Event.RegisterClick) },
                    modifier = Modifier
                        .weight(0.5f)
                )
                Text(
                    text = stringResource(R.string.login),
                    style = MuztacheTheme.typography.labelLarge,
                    color = MuztacheTheme.colors.textPrimary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .weight(0.5f)
                )
            }
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