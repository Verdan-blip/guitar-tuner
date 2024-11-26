package ru.muztache.feature.tuner.ui.engine.analyzer

import ru.muztache.feature.tuner.ui.engine.tone.Tone

interface PitchAnalyzer {

    fun analyze(pitch: Float): AnalyzeResult

    fun calculateDiff(pitch: Float, tone: Tone): Float
}