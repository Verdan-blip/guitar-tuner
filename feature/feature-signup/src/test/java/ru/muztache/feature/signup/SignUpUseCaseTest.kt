package ru.muztache.feature.signup

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import ru.muztache.core.data.local.auth.exceptions.SignUpException
import ru.muztache.core.data.local.auth.repository.AuthRepository
import ru.muztache.feature.signup.domain.entity.UserForm
import ru.muztache.feature.signup.domain.mapper.toDataUserForm
import ru.muztache.feature.signup.domain.usecase.SignUpUseCase

class SignUpUseCaseTest {

    private val authRepository: AuthRepository = mockk()
    private val testDispatcher = Dispatchers.Unconfined
    private lateinit var signUpUseCase: SignUpUseCase

    @Before
    fun setUp() {
        signUpUseCase = SignUpUseCase(authRepository, testDispatcher)
    }

    @Test
    fun `invoke should call repository signUp with correct parameters`() = runBlocking {
        val userForm = UserForm("test@example.com", "password123")
        coEvery { authRepository.signUp(userForm.toDataUserForm()) } returns Unit

        signUpUseCase(userForm)

        coVerify(exactly = 1) { authRepository.signUp(userForm.toDataUserForm()) }
    }

    @Test(expected = SignUpException.SuchUserAlreadyExists::class)
    fun `invoke should throw SuchUserAlreadyExists when repository throws`() = runBlocking {
        val userForm = UserForm("test@example.com", "password123")
        coEvery { authRepository.signUp(userForm.toDataUserForm()) } throws SignUpException.SuchUserAlreadyExists()

        signUpUseCase(userForm)
    }

    @Test(expected = SignUpException.FailedToCreateUser::class)
    fun `invoke should throw FailedToCreateUser when repository throws`() = runBlocking {
        val userForm = UserForm("test@example.com", "password123")
        coEvery { authRepository.signUp(userForm.toDataUserForm()) } throws SignUpException.FailedToCreateUser()

        signUpUseCase(userForm)
    }
}
