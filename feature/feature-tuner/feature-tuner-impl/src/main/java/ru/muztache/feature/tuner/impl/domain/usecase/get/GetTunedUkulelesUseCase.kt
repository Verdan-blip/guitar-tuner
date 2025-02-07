package ru.muztache.feature.tuner.impl.domain.usecase.get

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import ru.muztache.feature.tuner.api.domain.entity.instrument.Ukulele
import ru.muztache.feature.tuner.api.domain.repository.TunedInstrumentRepository
import ru.muztache.feature.tuner.api.domain.usecase.get.GetTunedInstrumentsUseCase

class GetTunedUkulelesUseCase(
    private val ukuleleRepository: TunedInstrumentRepository<Ukulele>,
    private val dispatcher: CoroutineDispatcher
) : GetTunedInstrumentsUseCase<Ukulele> {

    override suspend fun invoke(): Flow<Map<String, Ukulele>> {
        return withContext(dispatcher) {
            ukuleleRepository.instruments
        }
    }
}
