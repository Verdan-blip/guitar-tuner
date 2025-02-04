package ru.muztache.core.data.local.auth.exceptions

sealed class AuthException : Exception() {

    class FailedToCreatedUser : AuthException()

    class FailedToLogIn : AuthException()
}