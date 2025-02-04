package ru.muztache.feature.profile.ui.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import ru.muztache.feature.profile.ui.ProfileViewModel

internal val uiModule = module {
    viewModelOf(::ProfileViewModel)
}