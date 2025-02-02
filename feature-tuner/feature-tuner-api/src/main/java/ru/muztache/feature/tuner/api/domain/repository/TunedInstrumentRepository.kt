package ru.muztache.feature.tuner.api.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.muztache.feature.tuner.api.domain.entity.instrument.StringInstrument

interface TunedInstrumentRepository<I : StringInstrument<*>> {

    val instruments: Flow<Map<String, I>>

    suspend fun save(name: String, instrument: I)

    suspend fun remove(name: String)
}