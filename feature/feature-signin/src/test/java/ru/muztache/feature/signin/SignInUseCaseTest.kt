package ru.muztache.feature.signin

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import ru.muztache.core.data.local.auth.exceptions.SignInException
import ru.muztache.core.data.local.auth.repository.AuthRepository
import ru.muztache.feature.signin.domain.entity.UserForm
import ru.muztache.feature.signin.domain.mapper.toDataUserForm
import ru.muztache.feature.signin.domain.usecase.SignInUseCase

class SignInUseCaseTest {

    private val authRepository: AuthRepository = mockk()
    private val testDispatcher = Dispatchers.Unconfined
    private lateinit var signInUseCase: SignInUseCase

    @Before
    fun setUp() {
        signInUseCase = SignInUseCase(authRepository, testDispatcher)
    }

    @Test
    fun `invoke should call repository signIn with correct parameters`() = runBlocking {
        val userForm = UserForm("test@example.com", "password123")
        coEvery { authRepository.signIn(userForm.toDataUserForm()) } returns Unit

        signInUseCase(userForm)

        coVerify(exactly = 1) { authRepository.signIn(userForm.toDataUserForm()) }
    }

    @Test(expected = SignInException.FailedToSignIn::class)
    fun `invoke should throw FailedToSignIn when repository throws`() = runBlocking {
        val userForm = UserForm("test@example.com", "password123")
        coEvery { authRepository.signIn(userForm.toDataUserForm()) } throws SignInException.FailedToSignIn()
        
        signInUseCase(userForm)
    }

    @Test(expected = SignInException.InvalidCredentials::class)
    fun `invoke should throw InvalidCredentials when repository throws`() = runBlocking {
        val userForm = UserForm("test@example.com", "password123")
        coEvery { authRepository.signIn(userForm.toDataUserForm()) } throws SignInException.InvalidCredentials()
        
        signInUseCase(userForm)
    }
}
