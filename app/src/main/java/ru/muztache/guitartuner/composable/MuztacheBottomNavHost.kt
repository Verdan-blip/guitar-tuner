package ru.muztache.guitartuner.composable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.muztache.feature.chords.impl.ui.ChordsScreen
import ru.muztache.feature.profile.ui.ProfileScreen
import ru.muztache.feature.tuner.impl.ui.TunerScreen
import ru.muztache.guitartuner.navigation.BottomNavRoute
import ru.muztache.guitartuner.navigation.Route

@Composable
fun MuztacheBottomNavHost(
    bottomNavController: NavHostController,
    navController: NavHostController,
    startDestination: Any,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = bottomNavController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable<BottomNavRoute.Tuner> {
            TunerScreen(
                modifier = Modifier
                    .fillMaxSize()
            )
        }
        composable<BottomNavRoute.Profile> {
            ProfileScreen(
                onNavigateToSignUp = {
                    navController.navigate(Route.SignUp)
                },
                onNavigateToSignIn = {
                    navController.navigate(Route.SignIn)
                },
                modifier = Modifier
                    .fillMaxSize()
            )
        }
        composable<BottomNavRoute.Chords> {
            ChordsScreen(
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }
}