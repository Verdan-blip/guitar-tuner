package ru.muztache.feature.splash.di

import org.koin.dsl.module
import ru.muztache.feature.splash.ui.di.uiModule

val featureSplashModule = module {
    includes(uiModule)
}