package ru.muztache.feature.profile.di

import org.koin.dsl.module
import ru.muztache.feature.profile.ui.di.uiModule

val featureProfileModule = module {
    includes(uiModule)
}