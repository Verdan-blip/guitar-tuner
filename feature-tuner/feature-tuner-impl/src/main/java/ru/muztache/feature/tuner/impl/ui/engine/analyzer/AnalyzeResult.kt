package ru.muztache.feature.tuner.impl.ui.engine.analyzer

import ru.muztache.feature.tuner.api.domain.entity.tone.ToneWithOctave
import ru.muztache.feature.tuner.impl.ui.entity.math.Deviation

sealed class AnalyzeResult(val frequency: Float) {

    class Success(
        val nearestTone: ToneWithOctave,
        val deviation: Deviation,
        val isTuned: Boolean,
        frequency: Float
    ) : AnalyzeResult(frequency)

    class Failure(frequency: Float) : AnalyzeResult(frequency)
}