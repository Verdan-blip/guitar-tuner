package ru.muztache.feature.splash.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import ru.muztache.core.common.base.mvi.BaseEffect
import ru.muztache.core.theme.MuztacheTheme
import ru.muztache.core.theme.R
import ru.muztache.core.theme.composable.progress.MuztacheProgressIndicator
import ru.muztache.feature.splash.ui.mvi.SplashEvent
import ru.muztache.feature.splash.ui.mvi.SplashState
import ru.muztache.feature.splash.ui.route.SplashScreenRoute

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    onNavigateToHomeScreen: () -> Unit
) {
    val viewModel = koinViewModel<SplashViewModel>()
    val state = viewModel.state.collectAsStateWithLifecycle()
    val effect = viewModel.effect.collectAsStateWithLifecycle(null)

    SplashScreenContent(
        modifier = modifier,
        state = state.value,
        onEvent = { event -> viewModel.reducer(event) }
    )

    LaunchedEffect(Unit) {
        viewModel.reducer(SplashEvent.Launch)
    }

    LaunchedEffect(effect.value) {
        when (val value = effect.value) {
            is BaseEffect.NavigateTo -> {
                if (value.route is SplashScreenRoute) {
                    onNavigateToHomeScreen()
                }
            }
            else -> Unit
        }
    }
}

@Composable
fun SplashScreenContent(
    modifier: Modifier = Modifier,
    state: SplashState,
    onEvent: (SplashEvent) -> Unit
) {
    Surface(
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            MuztacheProgressIndicator(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = stringResource(R.string.loading),
                style = MuztacheTheme.typography.titleSmall,
                color = MuztacheTheme.colors.onSurface,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Preview
@Composable
private fun SplashScreenPreview() {
    MuztacheTheme {
        SplashScreenContent(
            state = SplashState(isSplashShowing = true),
            onEvent = { },
            modifier = Modifier
                .fillMaxSize()
        )
    }
}