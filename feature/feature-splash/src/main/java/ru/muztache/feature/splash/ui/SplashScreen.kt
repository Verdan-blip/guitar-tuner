package ru.muztache.feature.splash.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import ru.muztache.core.common.base.mvi.BaseEffect
import ru.muztache.core.common.permissions.checkPermission
import ru.muztache.core.common.permissions.rememberRequestPermissionLauncher
import ru.muztache.core.common.permissions.shouldShowRequestPermissionRationale
import ru.muztache.core.theme.MuztacheTheme
import ru.muztache.core.theme.R
import ru.muztache.core.theme.composable.progress.MuztacheProgressIndicator
import ru.muztache.core.theme.snackbar.LocalMuztacheSnackBar
import ru.muztache.feature.splash.ui.mvi.Action
import ru.muztache.feature.splash.ui.mvi.Event
import ru.muztache.feature.splash.ui.route.SplashScreenRoute

private const val RECORD_AUDIO = android.Manifest.permission.RECORD_AUDIO

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    onNavigateToHomeScreen: () -> Unit
) {
    val viewModel = koinViewModel<SplashViewModel>()
    val effect = viewModel.effect.collectAsStateWithLifecycle(null)
    val action = viewModel.action.collectAsStateWithLifecycle(null)

    SplashScreenContent(
        modifier = modifier
    )

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

    val context = LocalContext.current
    val permissionLauncher = rememberRequestPermissionLauncher { isGranted ->
        if (isGranted) {
            viewModel.reducer(Event.PermissionGranted)
        } else {
            RECORD_AUDIO.shouldShowRequestPermissionRationale(context) { should ->
                if (should) {
                    viewModel.reducer(Event.PermissionDenied)
                } else {
                    viewModel.reducer(Event.PermissionDeniedUnquestionable)
                }
            }
        }
    }

    val snackbarState = LocalMuztacheSnackBar.current
    LaunchedEffect(action.value) {
        when (val value = action.value) {
            is Action.RequestPermissionForRecordAudio -> {
                if (RECORD_AUDIO.checkPermission(context)) {
                    viewModel.reducer(Event.PermissionGranted)
                } else {
                    permissionLauncher.launch(RECORD_AUDIO)
                }
            }
            is Action.ShowRationaleWithAction -> {
                val result = snackbarState.showSnackbar(
                    value.message, value.positiveButton
                )
                when (result) {
                    SnackbarResult.ActionPerformed -> {
                        viewModel.reducer(Event.ReRequestPermission)
                    }
                    SnackbarResult.Dismissed -> {
                        viewModel.reducer(Event.PermissionDenied)
                    }
                }
            }
            is Action.ShowRationale -> {
                snackbarState.showSnackbar(value.message)
            }
            else -> Unit
        }
    }

    LaunchedEffect(Unit) {
        viewModel.reducer(Event.Launch)
    }
}

@Composable
private fun SplashScreenContent(
    modifier: Modifier = Modifier
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
                color = MuztacheTheme.colors.textPrimary,
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
            modifier = Modifier
                .fillMaxSize()
        )
    }
}
