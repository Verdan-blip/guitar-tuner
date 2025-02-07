package ru.muztache.feature.tuner.impl.domain.usecase.save

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.muztache.feature.tuner.api.domain.entity.instrument.Ukulele
import ru.muztache.feature.tuner.api.domain.repository.TunedInstrumentRepository
import ru.muztache.feature.tuner.api.domain.usecase.save.SaveInstrumentUseCase

class SaveUkuleleUseCase(
    private val tunedInstrumentRepository: TunedInstrumentRepository<Ukulele>,
    private val dispatcher: CoroutineDispatcher
) : SaveInstrumentUseCase<Ukulele> {

    override suspend operator fun invoke(name: String, instrument: Ukulele) {
        return withContext(dispatcher) {
            tunedInstrumentRepository.save(name, instrument)
        }
    }
}
