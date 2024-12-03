package ru.muztache.feature.tuner.data.repository

import android.content.Context
import ru.muztache.feature.tuner.domain.entity.Tuning
import ru.muztache.feature.tuner.domain.repository.TuningRepository

class TuningRepositoryImpl(
    private val context: Context
) : TuningRepository {


    override suspend fun fetchAll(): List<Tuning> {
        TODO("Not yet implemented")
    }

    override suspend fun add(tuning: Tuning) {
        TODO("Not yet implemented")
    }

    override suspend fun remove(tuning: Tuning) {
        TODO("Not yet implemented")
    }
}