package ru.muztache.feature.tuner.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.muztache.feature.tuner.domain.entity.tuning.Tuning

interface TuningRepository<T : Tuning> {

    val savedTunings: Flow<Map<String, T>>

    suspend fun add(key: String, tuning: T)

    suspend fun remove(key: String)
}