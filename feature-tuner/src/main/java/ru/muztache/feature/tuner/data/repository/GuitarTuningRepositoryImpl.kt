package ru.muztache.feature.tuner.data.repository

import android.content.Context
import androidx.datastore.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.muztache.feature.tuner.data.mapper.toDomainGuitarTuningMap
import ru.muztache.feature.tuner.data.mapper.toGuitarTuning
import ru.muztache.feature.tuner.data.util.SavedGuitarTuningsSerializer
import ru.muztache.feature.tuner.domain.entity.tuning.GuitarTuning
import ru.muztache.feature.tuner.domain.repository.TuningRepository

private const val TUNING_DATASTORE_NAME = "tunings"

private val Context.tuningDataStore by dataStore(
    fileName = TUNING_DATASTORE_NAME,
    serializer = SavedGuitarTuningsSerializer
)

class GuitarTuningRepositoryImpl(
    private val context: Context
) : TuningRepository<GuitarTuning> {

    override val savedTunings: Flow<Map<String, GuitarTuning>> = context.tuningDataStore.data
        .map { tunings ->
            tunings.tuningsMap.toDomainGuitarTuningMap()
        }

    override suspend fun add(key: String, tuning: GuitarTuning) {
        context.tuningDataStore.updateData { tunings ->
            tunings.apply {
                tunings.tuningsMap[key] = tuning.toGuitarTuning()
            }
        }
    }

    override suspend fun remove(key: String) {
        context.tuningDataStore.updateData { tunings ->
            tunings.apply {
                tunings.tuningsMap.remove(key)
            }
        }
    }
}