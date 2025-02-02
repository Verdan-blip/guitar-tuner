package ru.muztache.guitartuner.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.serialization.Serializable
import ru.muztache.guitartuner.R

@Serializable
sealed class BottomNavRoute(
    @StringRes val labelId: Int,
    @DrawableRes val iconId: Int
) {

    @Serializable
    data object Tuner : BottomNavRoute(
        labelId = R.string.tuner,
        iconId = R.drawable.ic_tuner24
    )

    @Serializable
    data object Chords : BottomNavRoute(
        labelId = R.string.chords,
        iconId = R.drawable.ic_chords_24
    )

    @Serializable
    data object Profile : BottomNavRoute(
        labelId = R.string.profile,
        iconId = R.drawable.ic_person_24
    )
}

fun getBottomNavRoutes(): List<BottomNavRoute> = listOf(
    BottomNavRoute.Tuner, BottomNavRoute.Chords, BottomNavRoute.Profile
)