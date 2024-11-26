package ru.muztache.feature.tuner.ui.entity.instrument

import ru.muztache.feature.tuner.ui.engine.tone.Tone
import ru.muztache.feature.tuner.ui.entity.tuning.Tuning

abstract class StringInstrument {

    abstract val stringCount: Int

    abstract var tuning: Tuning

    fun getTone(stringNum: Int): Tone = tuning.getNote(stringNum)
}