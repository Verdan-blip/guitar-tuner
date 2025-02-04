package ru.muztache.core.data.local.auth.repository

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import ru.muztache.core.data.local.auth.entity.User
import ru.muztache.core.data.local.auth.entity.UserForm
import ru.muztache.core.data.local.auth.exceptions.AuthException

class AuthRepositoryImpl(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {

    private val _currentUser = MutableStateFlow<User?>(null)
    override val currentUser: Flow<User?> get() = _currentUser

    override suspend fun createUser(user: UserForm) {
        firebaseAuth.createUserWithEmailAndPassword(
            user.email, user.password
        ).addOnCompleteListener { task ->
            when (task.isSuccessful) {
                true -> {
                    firebaseAuth.currentUser?.also { firebaseUser ->
                        firebaseUser.email?.also { email ->
                            _currentUser.value = User(email)
                        }
                    }
                }
                false -> {
                    throw AuthException.FailedToCreatedUser()
                }
            }
        }
    }

    override suspend fun signIn(user: UserForm) {
        firebaseAuth.signInWithEmailAndPassword(
            user.email, user.password
        ).addOnCompleteListener { task ->
            when (task.isSuccessful) {
                true -> {
                    firebaseAuth.currentUser?.also { firebaseUser ->
                        firebaseUser.email?.also { email ->
                            _currentUser.value = User(email)
                        }
                    }
                }
                false -> {
                    throw AuthException.FailedToLogIn()
                }
            }
        }
    }

    override suspend fun signOut() {
        firebaseAuth.signOut()
    }
}