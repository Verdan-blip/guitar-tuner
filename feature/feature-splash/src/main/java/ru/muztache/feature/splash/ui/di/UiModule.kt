package ru.muztache.feature.splash.ui.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import ru.muztache.feature.splash.ui.SplashViewModel

internal val uiModule = module {
    viewModelOf(::SplashViewModel)
}
