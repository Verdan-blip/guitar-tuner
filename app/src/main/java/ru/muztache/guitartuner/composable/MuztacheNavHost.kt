package ru.muztache.guitartuner.composable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.muztache.feature.signin.ui.SignInScreen
import ru.muztache.feature.signup.ui.SignUpScreen
import ru.muztache.feature.splash.ui.SplashScreen
import ru.muztache.guitartuner.navigation.BottomNavRoute
import ru.muztache.guitartuner.navigation.Route
import ru.muztache.guitartuner.navigation.getBottomNavRoutes

@Composable
fun MuztacheNavHost(
    navController: NavHostController,
    startDestination: Route,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable<Route.Splash> {
            SplashScreen(
                onNavigateToHomeScreen = {
                    navController.navigate(Route.BottomNavigationRoute)
                },
                modifier = Modifier
                    .fillMaxSize()
            )
        }
        composable<Route.SignIn> {
            SignInScreen(
                onNavigateToProfile = {
                    navController.navigate(Route.BottomNavigationRoute)
                },
                onNavigateToSignUp = {
                    navController.navigate(Route.SignUp)
                }
            )
        }
        composable<Route.SignUp> {
            SignUpScreen(
                onNavigateToProfile = {
                    navController.navigate(Route.BottomNavigationRoute)
                },
                onNavigateToSignIn = {
                    navController.navigate(Route.SignIn)
                }
            )
        }
        composable<Route.BottomNavigationRoute> {
            val selectedIndex = remember { mutableIntStateOf(0) }
            val bottomNavController = rememberNavController()
            Scaffold(
                bottomBar = {
                    MuztacheBottomNavigationBar(
                        routes = getBottomNavRoutes(),
                        selectedIndex = selectedIndex,
                        navHostController = bottomNavController
                    )
                }
            ) { innerPaddings ->
                MuztacheBottomNavHost(
                    navController = navController,
                    bottomNavController = bottomNavController,
                    startDestination = BottomNavRoute.Tuner,
                    modifier = Modifier
                        .padding(innerPaddings)
                )
            }
        }
    }
}