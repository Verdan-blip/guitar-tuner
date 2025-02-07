package ru.muztache.core.data.impl.local.user.di

import com.google.firebase.auth.FirebaseAuth
import org.koin.dsl.module
import ru.muztache.core.data.impl.local.user.repository.UserProfileRepositoryImpl
import ru.muztache.core.data.local.user.repository.UserProfileRepository

internal val userModule = module {
    factory<UserProfileRepository> {
        UserProfileRepositoryImpl(FirebaseAuth.getInstance())
    }
}
