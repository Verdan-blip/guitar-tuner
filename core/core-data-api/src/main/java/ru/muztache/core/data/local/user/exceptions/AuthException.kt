package ru.muztache.core.data.local.user.exceptions

sealed class AuthException : Exception() {

    class Unauthorized : AuthException()
}