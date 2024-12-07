package ru.muztache.feature.tuner.data.util

import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import ru.muztache.feature.tuner.data.entity.SavedGuitarTunings
import java.io.InputStream
import java.io.OutputStream

object SavedGuitarTuningsSerializer : Serializer<SavedGuitarTunings> {

    override val defaultValue: SavedGuitarTunings = SavedGuitarTunings.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): SavedGuitarTunings {
        return try {
            SavedGuitarTunings.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            defaultValue
        }
    }

    override suspend fun writeTo(t: SavedGuitarTunings, output: OutputStream) {
        t.writeTo(output)
    }
}