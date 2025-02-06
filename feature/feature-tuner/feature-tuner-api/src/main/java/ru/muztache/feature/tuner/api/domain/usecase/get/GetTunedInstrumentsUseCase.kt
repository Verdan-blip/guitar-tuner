package ru.muztache.feature.tuner.api.domain.usecase.get

import kotlinx.coroutines.flow.Flow
import ru.muztache.feature.tuner.api.domain.entity.instrument.StringInstrument

interface GetTunedInstrumentsUseCase<I : StringInstrument<*>> {

    suspend operator fun invoke(): Flow<Map<String, I>>
}