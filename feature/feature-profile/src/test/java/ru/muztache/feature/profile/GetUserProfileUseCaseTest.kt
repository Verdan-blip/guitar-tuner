package ru.muztache.feature.profile

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import ru.muztache.core.data.local.user.entity.UserProfileEntity
import ru.muztache.core.data.local.user.exceptions.AuthException
import ru.muztache.core.data.local.user.repository.UserProfileRepository
import ru.muztache.feature.profile.domain.mapper.toUserProfile
import ru.muztache.feature.profile.domain.usecase.GetUserProfileUseCase

class GetUserProfileUseCaseTest {

    private val userProfileRepository: UserProfileRepository = mockk()
    private val testDispatcher = Dispatchers.Unconfined
    private lateinit var getUserProfileUseCase: GetUserProfileUseCase

    @Before
    fun setUp() {
        getUserProfileUseCase = GetUserProfileUseCase(
            userProfileRepository, testDispatcher
        )
    }

    @Test
    fun `invoke should return UserProfile when repository returns data`() = runBlocking {
        val dataUserProfile = UserProfileEntity(
            id = "1234",
            email = "user@mail.ru",
            name = "Alex",
            photoUri = "Url"
        )
        val expectedUserProfile = dataUserProfile.toUserProfile()
        coEvery { userProfileRepository.getUserProfile() } returns dataUserProfile

        val result = getUserProfileUseCase()

        assertEquals(expectedUserProfile, result)
        coVerify(exactly = 1) { userProfileRepository.getUserProfile() }
    }

    @Test(expected = AuthException.Unauthorized::class)
    fun `invoke should throw Unauthorized when repository throws Unauthorized`() = runBlocking {
        coEvery { userProfileRepository.getUserProfile() } throws AuthException.Unauthorized()

        getUserProfileUseCase()

        Unit
    }

    @Test
    fun `invoke should use Unconfined dispatcher`() = runBlocking {
        val dataUserProfile = UserProfileEntity(
            id = "1234",
            email = "user@mail.ru",
            name = "Alex",
            photoUri = "Url"
        )
        coEvery { userProfileRepository.getUserProfile() } returns dataUserProfile

        getUserProfileUseCase()

        coVerify(exactly = 1) { userProfileRepository.getUserProfile() }
    }
}
