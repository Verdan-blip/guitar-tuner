package ru.muztache.feature.tuner.domain.entity

import ru.muztache.feature.tuner.ui.engine.tone.Tone

abstract class Tuning {

    abstract val stringsCount: Int

    abstract fun getNote(stringNumber: Int): Tone
}