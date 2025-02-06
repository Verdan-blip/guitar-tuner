package ru.muztache.feature.tuner.impl.domain.usecase.get

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.muztache.feature.tuner.api.domain.entity.instrument.Ukulele
import ru.muztache.feature.tuner.api.domain.repository.ReservedInstrumentRepository
import ru.muztache.feature.tuner.api.domain.usecase.get.GetReservedInstrumentsUseCase

class GetReservedUkulelesUseCase(
    private val reservedInstrumentRepository: ReservedInstrumentRepository<Ukulele>,
    private val dispatcher: CoroutineDispatcher
) : GetReservedInstrumentsUseCase<Ukulele> {

    override suspend fun invoke(): Map<String, Ukulele> {
        return withContext(dispatcher) {
            reservedInstrumentRepository.getAll()
        }
    }
}