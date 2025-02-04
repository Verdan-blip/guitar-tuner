package ru.muztache.feature.signup.domain.exceptions

sealed class ValidationException : Exception() {

    class InvalidEmailFormat : ValidationException()

    class InvalidPasswordFormat : ValidationException()

    class PasswordsNotMatch : ValidationException()
}