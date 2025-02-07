package ru.muztache.feature.tuner.impl.domain.usecase.save

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.muztache.feature.tuner.api.domain.entity.instrument.Guitar
import ru.muztache.feature.tuner.api.domain.repository.TunedInstrumentRepository
import ru.muztache.feature.tuner.api.domain.usecase.save.SaveInstrumentUseCase

class SaveGuitarUseCase(
    private val tunedInstrumentRepository: TunedInstrumentRepository<Guitar>,
    private val dispatcher: CoroutineDispatcher
) : SaveInstrumentUseCase<Guitar> {

    override suspend operator fun invoke(name: String, instrument: Guitar) {
        return withContext(dispatcher) {
            tunedInstrumentRepository.save(name, instrument)
        }
    }
}
