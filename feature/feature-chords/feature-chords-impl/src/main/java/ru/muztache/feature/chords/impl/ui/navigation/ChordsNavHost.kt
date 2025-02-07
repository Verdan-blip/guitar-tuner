package ru.muztache.feature.chords.impl.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.muztache.feature.chords.impl.ui.screens.details.ChordDetailsType
import ru.muztache.feature.chords.impl.ui.screens.details.ChordsDetailsScreen
import ru.muztache.feature.chords.impl.ui.screens.library.ChordsLibraryScreen

@Composable
fun ChordsNavHost(
    modifier: Modifier = Modifier,
    startDestination: InnerRoute = InnerRoute.ChordsLibrary,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable<InnerRoute.ChordsLibrary> {
            ChordsLibraryScreen(
                onNavigateToBaseChords = {
                    navController.navigate(InnerRoute.BaseChordsDetails)
                },
                onNavigateToAdvancedChords = {
                    navController.navigate(InnerRoute.AdvancedChordsDetails)
                },
                modifier = Modifier
                    .fillMaxSize()
            )
        }
        composable<InnerRoute.BaseChordsDetails> {
            ChordsDetailsScreen(
                modifier = Modifier
                    .fillMaxSize(),
                chordDetailsType = ChordDetailsType.BASIC
            )
        }
        composable<InnerRoute.AdvancedChordsDetails> {
            ChordsDetailsScreen(
                modifier = Modifier
                    .fillMaxSize(),
                chordDetailsType = ChordDetailsType.ADVANCED
            )
        }
    }
}
