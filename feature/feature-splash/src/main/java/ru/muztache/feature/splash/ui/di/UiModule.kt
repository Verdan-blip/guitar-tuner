package ru.muztache.feature.splash.ui.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.muztache.feature.splash.ui.SplashViewModel

internal val uiModule = module {
    viewModel {
        SplashViewModel(get())
    }
}