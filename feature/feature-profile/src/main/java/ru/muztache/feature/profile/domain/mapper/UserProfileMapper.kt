package ru.muztache.feature.profile.domain.mapper

import ru.muztache.core.data.local.user.entity.UserProfileEntity
import ru.muztache.feature.profile.domain.entity.UserProfile

fun UserProfileEntity.toUserProfile(): UserProfile =
    UserProfile(
        id = id,
        email = email,
        name = name,
        photoUri = photoUri
    )