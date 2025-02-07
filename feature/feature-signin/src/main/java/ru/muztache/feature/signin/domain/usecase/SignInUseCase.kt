package ru.muztache.feature.signin.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.muztache.core.data.local.auth.repository.AuthRepository
import ru.muztache.feature.signin.domain.entity.UserForm
import ru.muztache.feature.signin.domain.mapper.toDataUserForm

class SignInUseCase(
    private val authRepository: AuthRepository,
    private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(userForm: UserForm) {
        withContext(dispatcher) {
            authRepository.signIn(userForm.toDataUserForm())
        }
    }
}
