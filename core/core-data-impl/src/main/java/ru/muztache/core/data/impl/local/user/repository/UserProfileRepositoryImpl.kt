package ru.muztache.core.data.impl.local.user.repository

import androidx.core.net.toUri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.userProfileChangeRequest
import ru.muztache.core.data.local.user.entity.UserProfileEntity
import ru.muztache.core.data.local.user.exceptions.AuthException
import ru.muztache.core.data.local.user.repository.UserProfileRepository

class UserProfileRepositoryImpl(
    private val firebaseAuth: FirebaseAuth
) : UserProfileRepository {

    override suspend fun getUserProfile(): UserProfileEntity {
        return requiredAuthorization { user ->
            UserProfileEntity(
                id = user.uid,
                email = user.email ?: "",
                name = if (user.displayName.isNullOrBlank()) null else user.displayName,
                photoUri = user.photoUrl?.toString()
            )
        }
    }

    override suspend fun changeName(name: String) {
        requiredAuthorization<Unit> {
            userProfileChangeRequest {
                displayName = name
            }
        }
    }

    override suspend fun changePhotoUri(uri: String) {
        requiredAuthorization<Unit> {
            userProfileChangeRequest {
                photoUri = uri.toUri()
            }
        }
    }

    private fun <T> requiredAuthorization(body: (FirebaseUser) -> T): T {
        val user = firebaseAuth.currentUser
        return if (user == null) {
            throw AuthException.Unauthorized()
        } else {
            body(user)
        }
    }
}