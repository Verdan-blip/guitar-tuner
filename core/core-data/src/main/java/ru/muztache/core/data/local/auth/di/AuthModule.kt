package ru.muztache.core.data.local.auth.di

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import org.koin.dsl.module
import ru.muztache.core.data.local.auth.repository.AuthRepository
import ru.muztache.core.data.local.auth.repository.AuthRepositoryImpl

internal val authModule = module {
    factory<AuthRepository> { AuthRepositoryImpl(Firebase.auth) }
}