package ru.muztache.feature.tuner.domain.entity

import ru.muztache.feature.tuner.ui.engine.tone.Tone

abstract class StringInstrument {

    abstract val stringCount: Int

    abstract var tuning: Tuning

    fun getTone(stringNum: Int): Tone = tuning.getNote(stringNum)
}