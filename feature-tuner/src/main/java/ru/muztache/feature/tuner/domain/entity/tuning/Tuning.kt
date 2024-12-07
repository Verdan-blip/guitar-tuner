package ru.muztache.feature.tuner.domain.entity.tuning

import ru.muztache.feature.tuner.domain.entity.tone.ToneWithOctave

open class Tuning(
    private val tunes: List<ToneWithOctave>
) {

    val stringsCount: Int = tunes.size

    open fun getNote(stringNumber: Int): ToneWithOctave = tunes[stringNumber]
}