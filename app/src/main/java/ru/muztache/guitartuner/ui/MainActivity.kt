package ru.muztache.guitartuner.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import ru.muztache.core.common.base.BaseActivity
import ru.muztache.core.theme.MuztacheTheme
import ru.muztache.core.theme.composable.surface.MuztacheSurface
import ru.muztache.guitartuner.composable.MuztacheNavHost
import ru.muztache.guitartuner.navigation.Route

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            MuztacheTheme {
                MuztacheSurface {
                    val navController = rememberNavController()
                    MuztacheNavHost(
                        navController = navController,
                        startDestination = Route.Splash
                    )
                }
            }
        }
    }
}