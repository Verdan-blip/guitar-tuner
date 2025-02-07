package ru.muztache.feature.tuner.impl.ui.entity

private const val PERCENTS_TO_FRACTION = 0.01f

@JvmInline
internal value class Percents(val value: Float)

internal fun Percents.toFraction(): Float = value * PERCENTS_TO_FRACTION

internal fun Float.toPercents(): Percents = Percents(this)

internal fun Int.toPercents(): Percents = Percents(this.toFloat())
