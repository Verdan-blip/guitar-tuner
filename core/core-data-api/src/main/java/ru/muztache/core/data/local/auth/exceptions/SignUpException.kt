package ru.muztache.core.data.local.auth.exceptions

sealed class SignUpException : Exception() {

    class SuchUserAlreadyExists : SignUpException()

    class FailedToCreateUser : SignInException()
}