package ru.muztache.feature.signup.domain.di

import org.koin.dsl.module
import ru.muztache.core.common.di.iODispatcherQualifier
import ru.muztache.feature.signup.domain.interactor.ValidateFormInteractor
import ru.muztache.feature.signup.domain.usecase.SignUpUseCase

internal val domainModule = module {
    factory { SignUpUseCase(get(), get(iODispatcherQualifier)) }
    factory { ValidateFormInteractor(get(iODispatcherQualifier)) }
}