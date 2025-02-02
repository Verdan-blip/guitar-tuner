package ru.muztache.feature.tuner.impl.data.repository.reserved

import ru.muztache.feature.tuner.api.domain.entity.instrument.Ukulele
import ru.muztache.feature.tuner.api.domain.entity.tone.Tone
import ru.muztache.feature.tuner.api.domain.entity.tone.ToneWithOctave
import ru.muztache.feature.tuner.api.domain.entity.tuning.UkuleleTuning
import ru.muztache.feature.tuner.api.domain.repository.ReservedInstrumentRepository

internal class ReservedUkuleleRepositoryImpl : ReservedInstrumentRepository<Ukulele> {

    private val ukuleleStandardTuned = Ukulele(
        tuning = UkuleleTuning(
            string1 = ToneWithOctave(Tone.A, 4),
            string2 = ToneWithOctave(Tone.E, 4),
            string3 = ToneWithOctave(Tone.C, 4),
            string4 = ToneWithOctave(Tone.G, 4)
        )
    )

    private val ukuleles: Map<String, Ukulele> = mapOf(
        "Standard" to ukuleleStandardTuned
    )

    override suspend fun getAll(): Map<String, Ukulele> {
        return ukuleles
    }
}