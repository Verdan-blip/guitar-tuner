package ru.muztache.feature.tuner.impl.data.mapper

import ru.muztache.feature.tuner.impl.data.entity.Tone
import ru.muztache.feature.tuner.impl.data.entity.ToneWithOctave

internal typealias DomainToneWithOctave = ru.muztache.feature.tuner.api.domain.entity.tone.ToneWithOctave
internal typealias DomainTone =  ru.muztache.feature.tuner.api.domain.entity.tone.Tone

internal fun Tone.toDomainTone(): DomainTone {
    return when (this) {
        Tone.A -> DomainTone.A
        Tone.A_DIES -> DomainTone.A_DIES
        Tone.B -> DomainTone.B
        Tone.C -> DomainTone.C
        Tone.C_DIES -> DomainTone.C_DIES
        Tone.D -> DomainTone.D
        Tone.D_DIES -> DomainTone.D_DIES
        Tone.E -> DomainTone.E
        Tone.F -> DomainTone.F
        Tone.F_DIES -> DomainTone.F_DIES
        Tone.G -> DomainTone.G
        Tone.G_DIES -> DomainTone.G_DIES
        Tone.UNRECOGNIZED -> DomainTone.UNRECOGNIZED
    }
}


internal fun DomainTone.toTone(): Tone {
    return when (this) {
        DomainTone.A -> Tone.A
        DomainTone.A_DIES -> Tone.A_DIES
        DomainTone.B -> Tone.B
        DomainTone.C -> Tone.C
        DomainTone.C_DIES -> Tone.C_DIES
        DomainTone.D -> Tone.D
        DomainTone.D_DIES -> Tone.D_DIES
        DomainTone.E -> Tone.E
        DomainTone.F -> Tone.F
        DomainTone.F_DIES -> Tone.F_DIES
        DomainTone.G -> Tone.G
        DomainTone.G_DIES -> Tone.G_DIES
        DomainTone.UNRECOGNIZED -> Tone.UNRECOGNIZED
    }
}

fun ToneWithOctave.toDomainToneWithOctave(): DomainToneWithOctave =
    DomainToneWithOctave(tone.toDomainTone(), octave)

fun DomainToneWithOctave.toToneWithOctave(): ToneWithOctave =
    ToneWithOctave.newBuilder()
        .setTone(tone.toTone())
        .setOctave(octave)
        .build()
