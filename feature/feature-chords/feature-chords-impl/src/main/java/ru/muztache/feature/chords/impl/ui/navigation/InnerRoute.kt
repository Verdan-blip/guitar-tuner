package ru.muztache.feature.chords.impl.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface InnerRoute {

    @Serializable
    data object ChordsLibrary : InnerRoute

    @Serializable
    data object BaseChordsDetails : InnerRoute

    @Serializable
    data object AdvancedChordsDetails : InnerRoute
}
