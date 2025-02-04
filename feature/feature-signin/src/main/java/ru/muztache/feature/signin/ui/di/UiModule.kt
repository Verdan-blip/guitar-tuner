package ru.muztache.feature.signin.ui.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import ru.muztache.feature.signin.ui.SignInViewModel

internal val uiModule = module {
    factoryOf(::SignInViewModel)
}