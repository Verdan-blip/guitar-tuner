package ru.muztache.feature.signup.ui.route

internal interface SignUpRoute {

    data object Profile : SignUpRoute

    data object SignIn : SignUpRoute
}
