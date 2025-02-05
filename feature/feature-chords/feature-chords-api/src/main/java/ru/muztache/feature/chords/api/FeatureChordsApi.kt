package ru.muztache.feature.chords.api

import ru.muztache.feature.chords.api.domain.repository.ReservedGuitarChordRepository

interface FeatureChordsApi {

    val reservedGuitarChordRepository: ReservedGuitarChordRepository
}