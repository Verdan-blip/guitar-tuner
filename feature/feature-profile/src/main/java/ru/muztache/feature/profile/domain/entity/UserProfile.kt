package ru.muztache.feature.profile.domain.entity

data class UserProfile(
    val id: String,
    val email: String,
    val name: String?,
    val photoUri: String?
)