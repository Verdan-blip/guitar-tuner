package ru.muztache.feature.tuner.data.mapper

import ru.muztache.feature.tuner.data.entity.GuitarTuning

typealias DomainGuitarTuning = ru.muztache.feature.tuner.domain.entity.tuning.GuitarTuning

fun GuitarTuning.toDomainGuitarTuning(): DomainGuitarTuning {
    return DomainGuitarTuning(
        string1.toDomainToneWithOctave(),
        string2.toDomainToneWithOctave(),
        string3.toDomainToneWithOctave(),
        string4.toDomainToneWithOctave(),
        string5.toDomainToneWithOctave(),
        string6.toDomainToneWithOctave()
    )
}

fun DomainGuitarTuning.toGuitarTuning(): GuitarTuning {
    return GuitarTuning.newBuilder()
        .setString1(string1.toToneWithOctave())
        .setString2(string2.toToneWithOctave())
        .setString3(string3.toToneWithOctave())
        .setString4(string4.toToneWithOctave())
        .setString5(string5.toToneWithOctave())
        .setString6(string6.toToneWithOctave())
        .build()
}

fun Map<String, GuitarTuning>.toDomainGuitarTuningMap(): Map<String, DomainGuitarTuning> {
    val domainMap = mutableMapOf<String, DomainGuitarTuning>()
    for ((key, value) in entries) {
        domainMap[key] = value.toDomainGuitarTuning()
    }
    return domainMap
}