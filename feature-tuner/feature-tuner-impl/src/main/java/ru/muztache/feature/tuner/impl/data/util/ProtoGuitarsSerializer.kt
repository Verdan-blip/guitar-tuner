package ru.muztache.feature.tuner.impl.data.util

import androidx.datastore.core.Serializer
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import ru.muztache.feature.tuner.impl.data.entity.TunedGuitars
import java.io.InputStream
import java.io.OutputStream

typealias ProtoTunedGuitars = TunedGuitars

object ProtoGuitarsSerializer : Serializer<ProtoTunedGuitars> {

    override val defaultValue: ProtoTunedGuitars = ProtoTunedGuitars.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): ProtoTunedGuitars {
        return try {
            ProtoTunedGuitars.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            defaultValue
        }
    }

    override suspend fun writeTo(t: ProtoTunedGuitars, output: OutputStream) {
        t.writeTo(output)
    }
}