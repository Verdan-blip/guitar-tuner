package ru.muztache.feature.chords.api.domain.usecase

import ru.muztache.feature.chords.api.domain.entity.Chord

interface GetReservedGuitarChordsUseCase {

    suspend operator fun invoke(): List<Chord>
}