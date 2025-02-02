package ru.muztache.feature.tuner.impl.data.util

import androidx.datastore.core.Serializer
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import ru.muztache.feature.tuner.impl.data.entity.TunedUkuleles
import java.io.InputStream
import java.io.OutputStream

typealias ProtoTunedUkuleles = TunedUkuleles

object ProtoUkulelesSerializer : Serializer<ProtoTunedUkuleles> {

    override val defaultValue: ProtoTunedUkuleles = ProtoTunedUkuleles.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): ProtoTunedUkuleles {
        return try {
            ProtoTunedUkuleles.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            defaultValue
        }
    }

    override suspend fun writeTo(t: ProtoTunedUkuleles, output: OutputStream) {
        t.writeTo(output)
    }
}