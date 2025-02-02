package ru.muztache.guitartuner.composable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.muztache.feature.tuner.impl.ui.TunerScreen
import ru.muztache.guitartuner.navigation.BottomNavRoute

@Composable
fun MuztacheBottomNavHost(
    navController: NavHostController,
    startDestination: BottomNavRoute,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable<BottomNavRoute.Tuner> {
            TunerScreen(
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }
}