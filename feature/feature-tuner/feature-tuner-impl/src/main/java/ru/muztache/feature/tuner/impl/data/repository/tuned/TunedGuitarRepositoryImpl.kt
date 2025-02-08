package ru.muztache.feature.tuner.impl.data.repository.tuned

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.muztache.feature.tuner.api.domain.entity.instrument.Guitar
import ru.muztache.feature.tuner.api.domain.repository.TunedInstrumentRepository
import ru.muztache.feature.tuner.impl.data.mapper.toGuitar
import ru.muztache.feature.tuner.impl.data.mapper.toProtoTunedGuitar
import ru.muztache.feature.tuner.impl.data.util.ProtoGuitarsSerializer
import ru.muztache.feature.tuner.impl.data.util.ProtoTunedGuitars

private val Context.guitarDataSource: DataStore<ProtoTunedGuitars> by dataStore(
    fileName = "tuned_guitars",
    serializer = ProtoGuitarsSerializer
)

internal class TunedGuitarRepositoryImpl(
    private val context: Context
) : TunedInstrumentRepository<Guitar> {

    override val instruments: Flow<Map<String, Guitar>> = context
        .guitarDataSource
        .data
        .map { guitars ->
            guitars.guitarsMap.mapValues { (_, guitar) ->
                guitar.toGuitar()
            }
        }

    override suspend fun save(name: String, instrument: Guitar) {
        context.guitarDataSource.updateData { data ->
            data.toBuilder()
                .putGuitars(name, instrument.toProtoTunedGuitar())
                .build()
        }
    }

    override suspend fun remove(name: String) {
        context.guitarDataSource.updateData { data ->
            data.toBuilder()
                .removeGuitars(name)
                .build()
        }
    }
}
