package ru.muztache.feature.tuner.api.domain.usecase.save

import ru.muztache.feature.tuner.api.domain.entity.instrument.StringInstrument

interface SaveInstrumentUseCase<I : StringInstrument<*>> {

    suspend operator fun invoke(name: String, instrument: I)
}