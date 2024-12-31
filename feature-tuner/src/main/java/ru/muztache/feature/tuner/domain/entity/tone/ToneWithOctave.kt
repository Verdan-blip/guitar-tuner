package ru.muztache.feature.tuner.domain.entity.tone

class ToneWithOctave(
    val tone: Tone,
    val octave: Int
) {
    val frequency: Float = tone.rootFrequency * octave

    override fun toString(): String = "$tone$octave"
}