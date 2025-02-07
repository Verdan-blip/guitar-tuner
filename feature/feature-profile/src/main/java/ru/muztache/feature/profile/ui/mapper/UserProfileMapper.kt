package ru.muztache.feature.profile.ui.mapper

import ru.muztache.core.common.provider.ResourceProvider
import ru.muztache.feature.profile.R
import ru.muztache.feature.profile.domain.entity.UserProfile
import ru.muztache.feature.profile.ui.entity.UserProfileModel

fun UserProfile.toUserProfileModel(
    resourceProvider: ResourceProvider
): UserProfileModel =
    UserProfileModel(
        id = id,
        name = name ?: resourceProvider.getString(R.string.user),
        email = email,
        photoUri = photoUri
    )
