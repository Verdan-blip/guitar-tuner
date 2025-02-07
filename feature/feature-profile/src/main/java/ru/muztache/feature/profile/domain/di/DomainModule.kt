package ru.muztache.feature.profile.domain.di

import org.koin.dsl.module
import ru.muztache.core.common.di.iODispatcherQualifier
import ru.muztache.feature.profile.domain.usecase.GetUserProfileUseCase

internal val domainModule = module {
    factory { GetUserProfileUseCase(get(), get(iODispatcherQualifier)) }
}
