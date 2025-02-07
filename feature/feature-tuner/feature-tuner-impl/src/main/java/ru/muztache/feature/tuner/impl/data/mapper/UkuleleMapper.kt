package ru.muztache.feature.tuner.impl.data.mapper

import ru.muztache.feature.tuner.api.domain.entity.instrument.Ukulele
import ru.muztache.feature.tuner.api.domain.entity.tuning.UkuleleTuning
import ru.muztache.feature.tuner.impl.data.entity.TunedUkulele

internal typealias ProtoTunedUkulele = TunedUkulele

internal fun ProtoTunedUkulele.toUkulele(): Ukulele = Ukulele(
    tuning = UkuleleTuning(
        string1.toDomainToneWithOctave(),
        string2.toDomainToneWithOctave(),
        string3.toDomainToneWithOctave(),
        string4.toDomainToneWithOctave()
    )
)

internal fun Ukulele.toProtoTunedUkulele(): ProtoTunedUkulele = ProtoTunedUkulele.newBuilder()
    .setString1(tuning.string1.toToneWithOctave())
    .setString2(tuning.string2.toToneWithOctave())
    .setString3(tuning.string3.toToneWithOctave())
    .setString4(tuning.string4.toToneWithOctave())
    .build()
