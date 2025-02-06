package ru.muztache.feature.chords.impl.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.muztache.feature.chords.api.domain.entity.Chord
import ru.muztache.feature.chords.api.domain.repository.GuitarChordRepository
import ru.muztache.feature.chords.api.domain.usecase.GetBasicChordsUseCase

class GetBasicChordsUseCaseImpl(
    private val guitarChordsRepository: GuitarChordRepository,
    private val dispatcher: CoroutineDispatcher
) : GetBasicChordsUseCase {

    override suspend fun invoke(): List<Chord> {
        return withContext(dispatcher) {
            guitarChordsRepository.getBasicChords()
        }
    }
}