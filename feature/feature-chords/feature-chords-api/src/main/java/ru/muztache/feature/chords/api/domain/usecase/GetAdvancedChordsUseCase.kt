package ru.muztache.feature.chords.api.domain.usecase

import ru.muztache.feature.chords.api.domain.entity.Chord

interface GetAdvancedChordsUseCase {

    suspend operator fun invoke(): List<Chord>
}
