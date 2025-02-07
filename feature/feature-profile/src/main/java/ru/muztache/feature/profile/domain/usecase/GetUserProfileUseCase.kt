package ru.muztache.feature.profile.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.muztache.core.data.local.user.repository.UserProfileRepository
import ru.muztache.feature.profile.domain.entity.UserProfile
import ru.muztache.feature.profile.domain.mapper.toUserProfile

class GetUserProfileUseCase(
    private val userProfileRepository: UserProfileRepository,
    private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(): UserProfile {
        return withContext(dispatcher) {
            userProfileRepository.getUserProfile().toUserProfile()
        }
    }
}
