package ru.muztache.feature.chords.impl.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.muztache.feature.chords.api.domain.entity.Chord
import ru.muztache.feature.chords.api.domain.repository.ReservedGuitarChordRepository
import ru.muztache.feature.chords.api.domain.usecase.GetReservedGuitarChordsUseCase

internal class GetReservedGuitarChordsUseCaseImpl(
    private val reservedGuitarChordRepository: ReservedGuitarChordRepository,
    private val dispatcher: CoroutineDispatcher
) : GetReservedGuitarChordsUseCase {

    override suspend operator fun invoke(): List<Chord> {
        return withContext(dispatcher) {
            reservedGuitarChordRepository.getChords()
        }
    }
}