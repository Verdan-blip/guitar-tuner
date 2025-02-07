package ru.muztache.feature.chords.impl.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.muztache.feature.chords.api.domain.entity.Chord
import ru.muztache.feature.chords.api.domain.repository.GuitarChordRepository
import ru.muztache.feature.chords.api.domain.usecase.GetAdvancedChordsUseCase

class GetAdvancedChordsUseCaseImpl(
    private val guitarChordsRepository: GuitarChordRepository,
    private val dispatcher: CoroutineDispatcher
) : GetAdvancedChordsUseCase {

    override suspend fun invoke(): List<Chord> {
        return withContext(dispatcher) {
            guitarChordsRepository.getAdvancedChords()
        }
    }
}
