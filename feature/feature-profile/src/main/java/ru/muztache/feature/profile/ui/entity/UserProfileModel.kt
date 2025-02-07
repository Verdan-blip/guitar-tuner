package ru.muztache.feature.profile.ui.entity

data class UserProfileModel(
    val id: String,
    val email: String,
    val name: String,
    val photoUri: String?
)
