package ru.muztache.feature.tuner.domain.entity.instrument

import ru.muztache.feature.tuner.domain.entity.tone.Tone
import ru.muztache.feature.tuner.domain.entity.tuning.Tuning

abstract class StringInstrument {

    abstract val stringCount: Int

    abstract var tuning: Tuning

    fun getTone(stringNum: Int): Tone = tuning.getNote(stringNum)
}