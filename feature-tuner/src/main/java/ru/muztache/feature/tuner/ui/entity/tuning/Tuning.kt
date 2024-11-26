package ru.muztache.feature.tuner.ui.entity.tuning

import ru.muztache.feature.tuner.ui.engine.tone.Tone

abstract class Tuning {

    abstract fun getNote(stringNumber: Int): Tone
}