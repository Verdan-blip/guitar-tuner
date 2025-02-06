package ru.muztache.feature.chords.api

import ru.muztache.feature.chords.api.domain.usecase.GetReservedGuitarChordsUseCase
import ru.muztache.feature.chords.api.domain.usecase.SaveGuitarChordUseCase

interface FeatureChordsApi {

    val saveGuitarChordUseCase: SaveGuitarChordUseCase

    val getReservedGuitarChordsUseCase: GetReservedGuitarChordsUseCase
}