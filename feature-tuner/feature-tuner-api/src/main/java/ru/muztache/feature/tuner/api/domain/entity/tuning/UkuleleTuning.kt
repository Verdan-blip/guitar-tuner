package ru.muztache.feature.tuner.api.domain.entity.tuning

import ru.muztache.feature.tuner.api.domain.entity.tone.ToneWithOctave

class UkuleleTuning(
    val string1: ToneWithOctave,
    val string2: ToneWithOctave,
    val string3: ToneWithOctave,
    val string4: ToneWithOctave
) : Tuning(
    listOf(string1, string2, string3, string4)
)