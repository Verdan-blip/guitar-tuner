package ru.muztache.feature.tuner.api.domain.usecase.get

import ru.muztache.feature.tuner.api.domain.entity.instrument.StringInstrument

interface GetReservedInstrumentsUseCase<I : StringInstrument<*>> {

    suspend operator fun invoke(): Map<String, I>
}