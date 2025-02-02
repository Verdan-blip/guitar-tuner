package ru.muztache.feature.tuner.impl.data.repository.tuned

import android.content.Context
import androidx.datastore.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.muztache.feature.tuner.api.domain.entity.instrument.Ukulele
import ru.muztache.feature.tuner.api.domain.repository.TunedInstrumentRepository
import ru.muztache.feature.tuner.impl.data.mapper.toProtoTunedUkulele
import ru.muztache.feature.tuner.impl.data.mapper.toUkulele
import ru.muztache.feature.tuner.impl.data.util.ProtoUkulelesSerializer

private val Context.ukulelesDataStore by dataStore(
    fileName = "tuned_ukuleles",
    serializer = ProtoUkulelesSerializer
)

internal class TunedUkulelesRepositoryImpl(
    private val context: Context
) : TunedInstrumentRepository<Ukulele> {

    override val instruments: Flow<Map<String, Ukulele>> = context
        .ukulelesDataStore
        .data
        .map { data ->
            data.ukulelesMap.mapValues {
                (_, ukulele) -> ukulele.toUkulele()
            }
        }

    override suspend fun save(name: String, instrument: Ukulele) {
        context.ukulelesDataStore.updateData { data ->
            data.toBuilder()
                .putUkuleles(name, instrument.toProtoTunedUkulele())
                .build()
        }
    }

    override suspend fun remove(name: String) {
        context.ukulelesDataStore.updateData { data ->
            data.toBuilder()
                .removeUkuleles(name)
                .build()
        }
    }
}