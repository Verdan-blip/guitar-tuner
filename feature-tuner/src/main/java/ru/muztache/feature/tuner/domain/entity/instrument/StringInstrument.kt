package ru.muztache.feature.tuner.domain.entity.instrument

import ru.muztache.feature.tuner.domain.entity.tone.ToneWithOctave
import ru.muztache.feature.tuner.domain.entity.tuning.Tuning

abstract class StringInstrument <T : Tuning>(
    val stringCount: Int,
    val tuning: T
) {

    fun getToneWithOctave(stringNum: Int): ToneWithOctave {
        require(stringNum in 0 ..< stringCount) {
            "String num must be in range 0..$stringCount"
        }
        return tuning.getNote(stringNum)
    }
}