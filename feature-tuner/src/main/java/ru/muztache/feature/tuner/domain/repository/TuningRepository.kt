package ru.muztache.feature.tuner.domain.repository

import ru.muztache.feature.tuner.domain.entity.Tuning

interface TuningRepository {

    suspend fun fetchAll(): List<Tuning>

    suspend fun add(tuning: Tuning)

    suspend fun remove(tuning: Tuning)
}