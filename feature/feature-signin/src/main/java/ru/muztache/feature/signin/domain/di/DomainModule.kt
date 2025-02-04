package ru.muztache.feature.signin.domain.di

import org.koin.dsl.module
import ru.muztache.core.common.di.iODispatcherQualifier
import ru.muztache.feature.signin.domain.usecase.SignInUseCase

internal val domainModule = module {
    factory { SignInUseCase(get(), get(iODispatcherQualifier)) }
}