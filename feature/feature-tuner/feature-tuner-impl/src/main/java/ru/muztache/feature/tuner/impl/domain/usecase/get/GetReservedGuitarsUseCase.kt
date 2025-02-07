package ru.muztache.feature.tuner.impl.domain.usecase.get

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.muztache.feature.tuner.api.domain.entity.instrument.Guitar
import ru.muztache.feature.tuner.api.domain.repository.ReservedInstrumentRepository
import ru.muztache.feature.tuner.api.domain.usecase.get.GetReservedInstrumentsUseCase

class GetReservedGuitarsUseCase(
    private val reservedInstrumentRepository: ReservedInstrumentRepository<Guitar>,
    private val dispatcher: CoroutineDispatcher
) : GetReservedInstrumentsUseCase<Guitar> {

    override suspend fun invoke(): Map<String, Guitar> {
        return withContext(dispatcher) {
            reservedInstrumentRepository.getAll()
        }
    }
}
