package ru.muztache.core.data.local.auth.repository

import kotlinx.coroutines.flow.Flow
import ru.muztache.core.data.local.auth.entity.User
import ru.muztache.core.data.local.auth.entity.UserForm
import ru.muztache.core.data.local.auth.exceptions.SignInException
import ru.muztache.core.data.local.auth.exceptions.SignUpException
import kotlin.jvm.Throws

interface AuthRepository {

    val currentUser: Flow<User?>

    @Throws(SignUpException::class)
    suspend fun signUp(user: UserForm)

    @Throws(SignInException::class)
    suspend fun signIn(user: UserForm)

    suspend fun signOut()
}