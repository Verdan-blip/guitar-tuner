package ru.muztache.feature.signup

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import ru.muztache.feature.signup.domain.exceptions.ValidationException
import ru.muztache.feature.signup.domain.interactor.ValidateFormInteractor

class ValidateFormInteractorTest {

    private lateinit var validateFormInteractor: ValidateFormInteractor
    private val testDispatcher = Dispatchers.Unconfined

    @Before
    fun setUp() {
        validateFormInteractor = ValidateFormInteractor(testDispatcher)
    }

    @Test
    fun `validateEmail should not throw exception for valid email`() = runBlocking {
        val validEmail = "test@example.com"
        validateFormInteractor.validateEmail(validEmail)
    }

    @Test(expected = ValidationException.InvalidEmailFormat::class)
    fun `validateEmail should throw InvalidEmailFormat for invalid email`() = runBlocking {
        val invalidEmail = "invalid-email"
        validateFormInteractor.validateEmail(invalidEmail)
    }

    @Test
    fun `validatePassword should not throw exception for valid password`() = runBlocking {
        val validPassword = "Password123"
        validateFormInteractor.validatePassword(validPassword)
    }

    @Test(expected = ValidationException.InvalidPasswordFormat::class)
    fun `validatePassword should throw InvalidPasswordFormat for invalid password`() = runBlocking {
        val invalidPassword = "short"
        validateFormInteractor.validatePassword(invalidPassword)
    }

    @Test
    fun `validatePasswordsMatch should not throw exception when passwords match`() = runBlocking {
        val password = "Password123"
        val confirmedPassword = "Password123"
        validateFormInteractor.validatePasswordsMatch(password, confirmedPassword)
    }

    @Test(expected = ValidationException.PasswordsNotMatch::class)
    fun `validatePasswordsMatch should throw PasswordsNotMatch when passwords do not match`() = runBlocking {
        val password = "Password123"
        val confirmedPassword = "DifferentPassword"
        validateFormInteractor.validatePasswordsMatch(password, confirmedPassword)
    }
}
