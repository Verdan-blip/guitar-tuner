package ru.muztache.feature.tuner.impl.data.mapper

import ru.muztache.feature.tuner.api.domain.entity.instrument.Guitar
import ru.muztache.feature.tuner.api.domain.entity.tuning.GuitarTuning
import ru.muztache.feature.tuner.impl.data.entity.TunedGuitar

internal typealias ProtoTunedGuitar = TunedGuitar

internal fun ProtoTunedGuitar.toGuitar(): Guitar = Guitar(
    tuning = GuitarTuning(
        string1.toDomainToneWithOctave(),
        string2.toDomainToneWithOctave(),
        string3.toDomainToneWithOctave(),
        string4.toDomainToneWithOctave(),
        string5.toDomainToneWithOctave(),
        string6.toDomainToneWithOctave()
    )
)

internal fun Guitar.toProtoTunedGuitar(): ProtoTunedGuitar = ProtoTunedGuitar.newBuilder()
    .setString1(tuning.string1.toToneWithOctave())
    .setString2(tuning.string2.toToneWithOctave())
    .setString3(tuning.string3.toToneWithOctave())
    .setString4(tuning.string4.toToneWithOctave())
    .setString5(tuning.string5.toToneWithOctave())
    .setString6(tuning.string6.toToneWithOctave())
    .build()
