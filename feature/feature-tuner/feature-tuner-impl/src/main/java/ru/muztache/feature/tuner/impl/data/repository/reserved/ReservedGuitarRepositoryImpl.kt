@file:Suppress("MagicNumber")
package ru.muztache.feature.tuner.impl.data.repository.reserved

import ru.muztache.feature.tuner.api.domain.entity.instrument.Guitar
import ru.muztache.feature.tuner.api.domain.entity.tone.Tone
import ru.muztache.feature.tuner.api.domain.entity.tone.ToneWithOctave
import ru.muztache.feature.tuner.api.domain.entity.tuning.GuitarTuning
import ru.muztache.feature.tuner.api.domain.repository.ReservedInstrumentRepository

internal class ReservedGuitarRepositoryImpl : ReservedInstrumentRepository<Guitar> {

    private val guitarStandardTuned = Guitar(
        tuning = GuitarTuning(
            string1 = ToneWithOctave(Tone.E, 4),
            string2 = ToneWithOctave(Tone.B, 3),
            string3 = ToneWithOctave(Tone.G, 3),
            string4 = ToneWithOctave(Tone.D, 3),
            string5 = ToneWithOctave(Tone.A, 2),
            string6 = ToneWithOctave(Tone.E, 2)
        )
    )

    private val guitarOpenDmTuned = Guitar(
        tuning = GuitarTuning(
            string1 = ToneWithOctave(Tone.D, 4),
            string2 = ToneWithOctave(Tone.A, 3),
            string3 = ToneWithOctave(Tone.F, 3),
            string4 = ToneWithOctave(Tone.D, 3),
            string5 = ToneWithOctave(Tone.A, 2),
            string6 = ToneWithOctave(Tone.D, 2)
        )
    )

    private val guitars: Map<String, Guitar> = mapOf(
        "Standard" to guitarStandardTuned,
        "OpenDm" to guitarOpenDmTuned
    )

    override suspend fun getAll(): Map<String, Guitar> {
        return guitars
    }
}
