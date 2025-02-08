package ru.muztache.guitartuner.composable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import ru.muztache.core.theme.composable.navigation.MuztacheBottomNavigation
import ru.muztache.feature.signin.ui.SignInScreen
import ru.muztache.feature.signup.ui.SignUpScreen
import ru.muztache.feature.splash.ui.SplashScreen
import ru.muztache.guitartuner.navigation.Route
import ru.muztache.guitartuner.navigation.bottomNavigationItems

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
                    navController.navigate(Route.BottomNavigationRoute) {
                        popUpTo<Route.Splash> {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                modifier = Modifier
                    .fillMaxSize()
            )
        }
        composable<Route.SignIn> {
            SignInScreen(
                onNavigateToProfile = {
                    navController.navigate(Route.BottomNavigationRoute) {
                        popUpTo<Route.SignIn> {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                onNavigateToSignUp = {
                    navController.navigate(Route.SignUp) {
                        launchSingleTop = true
                    }
                },
                modifier = Modifier
                    .fillMaxSize()
            )
        }
        composable<Route.SignUp> {
            SignUpScreen(
                onNavigateToProfile = {
                    navController.navigate(Route.BottomNavigationRoute) {
                        popUpTo<Route.SignUp> {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                onNavigateToSignIn = {
                    navController.navigate(Route.SignIn) {
                        launchSingleTop = true
                    }
                },
                modifier = Modifier
                    .fillMaxSize()
            )
        }
        composable<Route.BottomNavigationRoute> {
            val bottomNavController = rememberNavController()
            val bottomNavItems = bottomNavigationItems
            val startIndex = 0
            val selectedIndex = remember { mutableIntStateOf(startIndex) }
            Scaffold(
                bottomBar = {
                    MuztacheBottomNavigation(
                        selectedItemIndex = selectedIndex.intValue,
                        items = bottomNavItems,
                        onClick = { item ->
                            bottomNavController.navigate(item.route) {
                                launchSingleTop = true
                                popUpTo(bottomNavItems[startIndex].route)
                            }
                            selectedIndex.intValue = bottomNavItems.indexOf(item)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            ) { innerPaddings ->
                MuztacheBottomNavHost(
                    navController = navController,
                    bottomNavController = bottomNavController,
                    startDestination = bottomNavItems[startIndex].route,
                    modifier = Modifier
                        .padding(innerPaddings)
                )
            }
        }
    }
}
