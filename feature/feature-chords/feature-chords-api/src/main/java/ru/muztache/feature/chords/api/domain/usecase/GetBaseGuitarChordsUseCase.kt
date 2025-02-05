package ru.muztache.feature.chords.api.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.muztache.feature.chords.api.domain.entity.Chord
import ru.muztache.feature.chords.api.domain.repository.GuitarChordsRepository

class GetBaseGuitarChordsUseCase(
    private val guitarChordsRepository: GuitarChordsRepository,
    private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(): List<Chord> {
        return withContext(dispatcher) {
            guitarChordsRepository.getBaseChords()
        }
    }
}