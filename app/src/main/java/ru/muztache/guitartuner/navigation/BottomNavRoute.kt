package ru.muztache.guitartuner.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import kotlinx.serialization.Serializable
import ru.muztache.core.theme.composable.navigation.BottomNavItem
import ru.muztache.guitartuner.R

@Serializable
sealed interface BottomNavRoute {

    @Serializable
    data object Tuner : BottomNavRoute

    @Serializable
    data object Chords : BottomNavRoute

    @Serializable
    data object Profile : BottomNavRoute
}

val bottomNavigationItems: List<BottomNavItem> @Composable get() = listOf(
    BottomNavItem(
        ImageVector.vectorResource(R.drawable.ic_chords_24), BottomNavRoute.Chords
    ),
    BottomNavItem(
        ImageVector.vectorResource(R.drawable.ic_tuner24), BottomNavRoute.Tuner
    ),
    BottomNavItem(
        ImageVector.vectorResource(R.drawable.ic_person_24), BottomNavRoute.Profile
    )
)