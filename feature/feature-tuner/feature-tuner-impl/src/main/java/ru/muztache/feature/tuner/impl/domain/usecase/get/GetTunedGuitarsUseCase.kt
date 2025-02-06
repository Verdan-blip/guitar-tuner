package ru.muztache.feature.tuner.impl.domain.usecase.get

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import ru.muztache.feature.tuner.api.domain.entity.instrument.Guitar
import ru.muztache.feature.tuner.api.domain.repository.TunedInstrumentRepository
import ru.muztache.feature.tuner.api.domain.usecase.get.GetTunedInstrumentsUseCase

class GetTunedGuitarsUseCase(
    private val guitarRepository: TunedInstrumentRepository<Guitar>,
    private val dispatcher: CoroutineDispatcher
) : GetTunedInstrumentsUseCase<Guitar> {

    override suspend fun invoke(): Flow<Map<String, Guitar>> {
        return withContext(dispatcher) {
            guitarRepository.instruments
        }
    }
}