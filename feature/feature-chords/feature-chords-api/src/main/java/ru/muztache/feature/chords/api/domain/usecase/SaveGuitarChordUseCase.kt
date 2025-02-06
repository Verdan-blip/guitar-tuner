package ru.muztache.feature.chords.api.domain.usecase

import ru.muztache.feature.chords.api.domain.entity.Chord

interface SaveGuitarChordUseCase {

    suspend operator fun invoke(chords: List<Chord>)
}