package ru.muztache.feature.tuner.domain.entity.tuning

import ru.muztache.feature.tuner.domain.entity.tone.ToneWithOctave

open class Tuning(private val tunes: List<ToneWithOctave>) {

    val stringsCount: Int = tunes.size

    fun getNote(stringNumber: Int): ToneWithOctave = tunes[stringNumber]
}

inline fun Tuning.forEachTone(skipIndex: Int = 0, action: (ToneWithOctave) -> Unit) {
    for (i in skipIndex + 1 ..< stringsCount) {
        action(getNote(i))
    }
}