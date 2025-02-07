package ru.muztache.feature.chords.impl

import ru.muztache.feature.chords.api.FeatureChordsApi
import ru.muztache.feature.chords.api.domain.usecase.GetReservedGuitarChordsUseCase
import ru.muztache.feature.chords.api.domain.usecase.SaveGuitarChordUseCase

internal class FeatureChordImpl(
    override val saveGuitarChordUseCase: SaveGuitarChordUseCase,
    override val getReservedGuitarChordsUseCase: GetReservedGuitarChordsUseCase
) : FeatureChordsApi
