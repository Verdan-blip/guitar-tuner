package ru.muztache.feature.tuner.ui.engine.analyzer

import ru.muztache.feature.tuner.ui.engine.tone.Tone
import ru.muztache.feature.tuner.ui.entity.math.Deviation

sealed class AnalyzeResult(val frequency: Float) {

    class Success(
        val nearestTone: Tone,
        val deviation: Deviation,
        val isTuned: Boolean,
        frequency: Float
    ) : AnalyzeResult(frequency)

    class Failure(frequency: Float) : AnalyzeResult(frequency)
}