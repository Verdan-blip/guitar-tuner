package ru.muztache.feature.profile.ui.entity

sealed interface AuthResult {

    data object Pending : AuthResult

    data object UnAuthorized : AuthResult

    data class Authorized(val userProfile: UserProfileModel) : AuthResult
}