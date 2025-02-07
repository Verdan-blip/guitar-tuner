package ru.muztache.feature.tuner.impl.ui.entity

import kotlin.math.log2

@JvmInline
internal value class Deviation(val value: Float) {

    constructor(frequencyToCompare: Float, frequency: Float) :
            this(fromFrequencyRatio(frequencyToCompare / frequency))

    companion object {

        fun fromFrequencyRatio(ratio: Float): Float = 1200 * log2(ratio)
    }
}

internal operator fun Deviation.compareTo(other: Deviation): Int = value.compareTo(other.value)

internal fun Deviation.toPercents(): Percents = Percents(value * 0.0058f)

internal fun Percents.toDeviation(): Deviation = Deviation(value / 0.0058f)
