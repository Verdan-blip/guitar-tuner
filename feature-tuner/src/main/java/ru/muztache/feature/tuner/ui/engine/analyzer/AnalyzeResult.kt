package ru.muztache.feature.tuner.ui.engine.analyzer

import ru.muztache.feature.tuner.ui.engine.tone.Tone

sealed class AnalyzeResult(val pitch: Float) {

    class Success(
        val nearestTone: Tone,
        val diff: Float,
        pitch: Float
    ) : AnalyzeResult(pitch)

    class Failure(
        pitch: Float
    ) : AnalyzeResult(pitch)
}