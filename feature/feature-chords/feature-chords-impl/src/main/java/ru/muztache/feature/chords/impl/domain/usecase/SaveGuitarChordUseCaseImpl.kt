package ru.muztache.feature.chords.impl.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.muztache.core.data.local.chords.datasource.GuitarChordDataSource
import ru.muztache.feature.chords.api.domain.entity.Chord
import ru.muztache.feature.chords.api.domain.usecase.SaveGuitarChordUseCase
import ru.muztache.feature.chords.impl.data.mapper.toChordEntityList

class SaveGuitarChordUseCaseImpl(
    private val guitarChordDataSource: GuitarChordDataSource,
    private val dispatcher: CoroutineDispatcher
) : SaveGuitarChordUseCase {

    override suspend fun invoke(chords: List<Chord>) {
        withContext(dispatcher) {
            guitarChordDataSource.insert(chords.toChordEntityList())
        }
    }
}