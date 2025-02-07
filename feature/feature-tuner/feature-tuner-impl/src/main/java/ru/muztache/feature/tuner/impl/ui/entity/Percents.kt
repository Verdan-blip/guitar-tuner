package ru.muztache.feature.tuner.impl.ui.entity

@JvmInline
internal value class Percents(val value: Float)

internal fun Percents.toFraction(): Float = value / 100f

internal fun Float.toPercents(): Percents = Percents(this)

internal fun Int.toPercents(): Percents = Percents(this.toFloat())
