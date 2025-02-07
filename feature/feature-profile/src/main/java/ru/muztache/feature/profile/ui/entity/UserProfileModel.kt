package ru.muztache.feature.profile.ui.entity

import ru.muztache.core.common.entity.TextFieldState

data class UserProfileModel(
    val id: String,
    val email: String,
    val name: TextFieldState,
    val photoUri: String?
)