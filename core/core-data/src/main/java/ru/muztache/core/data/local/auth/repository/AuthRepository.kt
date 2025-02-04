package ru.muztache.core.data.local.auth.repository

import kotlinx.coroutines.flow.Flow
import ru.muztache.core.data.local.auth.entity.User
import ru.muztache.core.data.local.auth.entity.UserForm

interface AuthRepository {

    val currentUser: Flow<User?>

    suspend fun createUser(user: UserForm)

    suspend fun signIn(user: UserForm)

    suspend fun signOut()
}