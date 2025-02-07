package ru.muztache.feature.chords.impl.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.koinViewModel
import ru.muztache.core.common.base.mvi.BaseEffect
import ru.muztache.feature.chords.impl.ui.navigation.ChordsNavHost
import ru.muztache.feature.chords.impl.ui.navigation.InnerRoute
import ru.muztache.feature.chords.impl.ui.screens.library.ChordsViewModel

@Composable
fun ChordsScreen(
    modifier: Modifier = Modifier
) {
    val viewModel = koinViewModel<ChordsViewModel>()
    val effect = viewModel.effect.collectAsStateWithLifecycle(null)
    val navController = rememberNavController()

    ChordsScreenContent(
        navController = navController,
        modifier = modifier
    )

    LaunchedEffect(effect.value) {
        when (val value = effect.value) {
            is BaseEffect.NavigateTo -> {
                when (value.route) {
                    is InnerRoute.BaseChordsDetails -> {
                        navController.navigate(InnerRoute.BaseChordsDetails)
                    }
                    is InnerRoute.AdvancedChordsDetails -> {
                        navController.navigate(InnerRoute.AdvancedChordsDetails)
                    }
                }
            }
            else -> Unit
        }
    }
}

@Composable
fun ChordsScreenContent(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    ChordsNavHost(
        navController = navController,
        modifier = modifier
    )
}

@Preview
@Composable
private fun ChordsScreenPreview() {
    ChordsScreen(
        modifier = Modifier
            .fillMaxSize()
    )
}
