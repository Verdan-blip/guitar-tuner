package ru.muztache.core.data.local.user.repository

import ru.muztache.core.data.local.user.entity.UserProfileEntity
import ru.muztache.core.data.local.user.exceptions.AuthException
import kotlin.jvm.Throws

interface UserProfileRepository {

    @Throws(AuthException.Unauthorized::class)
    suspend fun getUserProfile(): UserProfileEntity

    @Throws(AuthException.Unauthorized::class)
    suspend fun changeName(name: String)

    @Throws(AuthException.Unauthorized::class)
    suspend fun changePhotoUri(uri: String)
}