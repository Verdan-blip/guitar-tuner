package ru.muztache.guitartuner.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import ru.muztache.core.common.base.BaseActivity
import ru.muztache.core.theme.MuztacheTheme
import ru.muztache.core.theme.snackbar.LocalMuztacheSnackBar
import ru.muztache.guitartuner.composable.MuztacheNavHost
import ru.muztache.guitartuner.navigation.Route

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            MuztacheTheme {
                val navController = rememberNavController()
                Scaffold(
                    containerColor = MuztacheTheme.colors.background,
                    snackbarHost = {
                        SnackbarHost(hostState = LocalMuztacheSnackBar.current)
                    },
                ) { _ ->
                    MuztacheNavHost(
                        navController = navController,
                        startDestination = Route.Splash
                    )
                }
            }
        }
    }
}