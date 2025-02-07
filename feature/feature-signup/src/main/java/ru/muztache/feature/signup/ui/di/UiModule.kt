package ru.muztache.feature.signup.ui.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import ru.muztache.feature.signup.ui.SignUpViewModel

internal val uiModule = module {
    viewModelOf(::SignUpViewModel)
}
