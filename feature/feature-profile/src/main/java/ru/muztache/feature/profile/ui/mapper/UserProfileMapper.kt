package ru.muztache.feature.profile.ui.mapper

import ru.muztache.core.common.entity.TextFieldState
import ru.muztache.feature.profile.domain.entity.UserProfile
import ru.muztache.feature.profile.ui.entity.UserProfileModel

fun UserProfile.toUserProfileModel(): UserProfileModel =
    UserProfileModel(
        id = id,
        name = TextFieldState.Idle(name ?: ""),
        email = email,
        photoUri = photoUri
    )