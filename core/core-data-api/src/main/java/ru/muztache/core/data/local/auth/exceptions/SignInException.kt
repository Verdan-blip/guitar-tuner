package ru.muztache.core.data.local.auth.exceptions

sealed class SignInException : Exception() {

    class InvalidCredentials : SignInException()

    class FailedToSignIn : SignInException()
}