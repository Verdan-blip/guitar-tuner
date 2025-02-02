package ru.muztache.feature.tuner.api.domain.repository

import ru.muztache.feature.tuner.api.domain.entity.instrument.StringInstrument

interface ReservedInstrumentRepository<I : StringInstrument<*>> {

    suspend fun getAll(): Map<String, I>
}