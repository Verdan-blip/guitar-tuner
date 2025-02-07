package ru.muztache.feature.chords.impl.ui.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import ru.muztache.feature.chords.impl.ui.screens.details.ChordDetailsViewModel
import ru.muztache.feature.chords.impl.ui.screens.library.ChordsViewModel

internal val uiModule = module {
    viewModelOf(::ChordsViewModel)
    viewModelOf(::ChordDetailsViewModel)
}
