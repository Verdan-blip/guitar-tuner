package ru.muztache.feature.profile.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.muztache.core.data.local.user.repository.UserProfileRepository

class ChangeUserNameUseCase(
    private val userProfileRepository: UserProfileRepository,
    private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(name: String) {
        withContext(dispatcher) {
            userProfileRepository.changeName(name)
        }
    }
}