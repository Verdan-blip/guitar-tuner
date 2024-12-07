package ru.muztache.feature.tuner.domain.entity.tuning

import ru.muztache.feature.tuner.domain.entity.tone.ToneWithOctave

class GuitarTuning(
    val string1: ToneWithOctave,
    val string2: ToneWithOctave,
    val string3: ToneWithOctave,
    val string4: ToneWithOctave,
    val string5: ToneWithOctave,
    val string6: ToneWithOctave,
) : Tuning(
    listOf(string1, string2, string3, string4, string5, string6)
)