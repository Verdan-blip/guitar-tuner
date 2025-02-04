package ru.muztache.feature.signup.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.muztache.core.data.local.auth.repository.AuthRepository
import ru.muztache.feature.signup.domain.entity.UserForm
import ru.muztache.feature.signup.domain.mapper.toDataUserForm

class SignUpUseCase(
    private val authRepository: AuthRepository,
    private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(userForm: UserForm) {
        withContext(dispatcher) {
            authRepository.createUser(userForm.toDataUserForm())
        }
    }
}