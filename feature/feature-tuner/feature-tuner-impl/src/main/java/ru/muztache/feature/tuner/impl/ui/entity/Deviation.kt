package ru.muztache.feature.tuner.impl.ui.entity

import kotlin.math.log2

private const val RATIO_TO_DEVIATION = 1200
private const val DEVIATION_TO_PERCENTS = 0.0058f

@JvmInline
internal value class Deviation(val value: Float) {

    constructor(frequencyToCompare: Float, frequency: Float) :
            this(fromFrequencyRatio(frequencyToCompare / frequency))

    companion object {

        fun fromFrequencyRatio(ratio: Float): Float = RATIO_TO_DEVIATION * log2(ratio)
    }
}

internal operator fun Deviation.compareTo(other: Deviation): Int = value.compareTo(other.value)

internal fun Deviation.toPercents(): Percents = Percents(value * DEVIATION_TO_PERCENTS)

internal fun Percents.toDeviation(): Deviation = Deviation(value / DEVIATION_TO_PERCENTS)
