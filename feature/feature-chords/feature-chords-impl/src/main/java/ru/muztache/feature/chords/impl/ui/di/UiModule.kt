package ru.muztache.feature.chords.impl.ui.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import ru.muztache.feature.chords.impl.ui.ChordsViewModel

internal val uiModule = module {
    viewModelOf(::ChordsViewModel)
}