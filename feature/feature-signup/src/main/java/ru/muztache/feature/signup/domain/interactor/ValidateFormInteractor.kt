package ru.muztache.feature.signup.domain.interactor

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.muztache.feature.signup.domain.exceptions.ValidationException

class ValidateFormInteractor(
    private val dispatcher: CoroutineDispatcher
) {

    suspend fun validateEmail(email: String) {
        withContext(dispatcher) {
            if (email.matches(EMAIL_REGEX).not()) {
                throw ValidationException.InvalidEmailFormat()
            }
        }
    }

    suspend fun validatePassword(password: String) {
        withContext(dispatcher) {
            if (password.matches(PASSWORD_REGEX).not()) {
                throw ValidationException.InvalidPasswordFormat()
            }
        }
    }

    suspend fun validatePasswordsMatch(password: String, confirmedPassword: String) {
        withContext(dispatcher) {
            if (password != confirmedPassword) {
                throw ValidationException.PasswordsNotMatch()
            }
        }
    }

    companion object {

        private val EMAIL_REGEX = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}\$".toRegex()
        private val PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}\$".toRegex()
    }
}