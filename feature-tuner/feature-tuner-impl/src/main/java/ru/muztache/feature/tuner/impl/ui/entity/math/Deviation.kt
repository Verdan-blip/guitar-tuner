package ru.muztache.feature.tuner.impl.ui.entity.math

import kotlin.math.log2

@JvmInline
value class Deviation(val value: Float) {

    constructor(frequencyToCompare: Float, frequency: Float) :
            this(fromFrequencyRatio(frequencyToCompare / frequency))

    companion object {

        fun fromFrequencyRatio(ratio: Float): Float = 1200 * log2(ratio)
    }
}

operator fun Deviation.compareTo(other: Deviation): Int = value.compareTo(other.value)

fun Deviation.toPercents(): Percents = Percents(value * 0.0058f)

fun Percents.toDeviation(): Deviation = Deviation(value / 0.0058f)
