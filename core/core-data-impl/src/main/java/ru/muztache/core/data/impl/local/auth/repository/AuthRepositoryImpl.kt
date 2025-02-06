package ru.muztache.core.data.impl.local.auth.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.tasks.await
import ru.muztache.core.data.local.auth.entity.User
import ru.muztache.core.data.local.auth.entity.UserForm
import ru.muztache.core.data.local.auth.exceptions.SignInException
import ru.muztache.core.data.local.auth.exceptions.SignUpException
import ru.muztache.core.data.local.auth.repository.AuthRepository

class AuthRepositoryImpl(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {

    private val _currentUser = MutableStateFlow<User?>(null)
    override val currentUser: Flow<User?> get() = _currentUser

    override suspend fun signUp(user: UserForm) {
        try {
            val userCredentials = firebaseAuth
                .createUserWithEmailAndPassword(user.email, user.password)
                .await()
            userCredentials.user?.apply {
                email?.also { validEmail ->
                    _currentUser.emit(User(validEmail))
                }
            }
        } catch (ex: Exception) {
            when (ex) {
                is FirebaseAuthUserCollisionException -> {
                    throw SignUpException.SuchUserAlreadyExists()
                }
                else -> {
                    throw SignUpException.FailedToCreateUser()
                }
            }
        }
    }

    override suspend fun signIn(user: UserForm) {
        try {
            val userCredentials = firebaseAuth
                .signInWithEmailAndPassword(user.email, user.password)
                .await()
            userCredentials.user?.apply {
                email?.also { validEmail ->
                    _currentUser.emit(User(validEmail))
                }
            }
        } catch (ex: Exception) {
            when (ex) {
                is FirebaseAuthInvalidCredentialsException -> {
                    throw SignInException.InvalidCredentials()
                }
                else -> {
                    throw SignInException.FailedToSignIn()
                }
            }
        }
    }

    override suspend fun signOut() {
        firebaseAuth.signOut()
    }
}