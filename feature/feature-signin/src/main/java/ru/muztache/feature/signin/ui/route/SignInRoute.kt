package ru.muztache.feature.signin.ui.route

sealed interface SignInRoute {

    data object Profile : SignInRoute

    data object SignUp : SignInRoute
}