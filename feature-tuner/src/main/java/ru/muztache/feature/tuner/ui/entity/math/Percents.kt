package ru.muztache.feature.tuner.ui.entity.math

@JvmInline
value class Percents(val value: Float) {

}

fun Percents.toFraction(): Float = value / 100f

fun Float.toPercents(): Percents = Percents(this)

fun Int.toPercents(): Percents = Percents(this.toFloat())