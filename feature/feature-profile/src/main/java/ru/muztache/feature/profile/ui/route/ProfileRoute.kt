package ru.muztache.feature.profile.ui.route

sealed interface ProfileRoute {

    data object SignIn : ProfileRoute

    data object SignUp : ProfileRoute
}
