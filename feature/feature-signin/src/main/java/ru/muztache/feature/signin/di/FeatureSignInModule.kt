package ru.muztache.feature.signin.di

import org.koin.dsl.module
import ru.muztache.feature.signin.domain.di.domainModule
import ru.muztache.feature.signin.ui.di.uiModule

val featureSignInModule = module {
    includes(domainModule)
    includes(uiModule)
}
