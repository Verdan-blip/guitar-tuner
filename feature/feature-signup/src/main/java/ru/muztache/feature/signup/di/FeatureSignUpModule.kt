package ru.muztache.feature.signup.di

import org.koin.dsl.module
import ru.muztache.feature.signup.domain.di.domainModule
import ru.muztache.feature.signup.ui.di.uiModule

val featureSignUpModule = module {
    includes(domainModule)
    includes(uiModule)
}
