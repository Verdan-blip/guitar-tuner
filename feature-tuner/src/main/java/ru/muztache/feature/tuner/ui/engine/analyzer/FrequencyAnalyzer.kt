package ru.muztache.feature.tuner.ui.engine.analyzer

import ru.muztache.feature.tuner.ui.engine.tone.Tone

interface FrequencyAnalyzer {

    fun analyze(frequency: Float): AnalyzeResult

    fun analyzeComparing(frequency: Float, tone: Tone): AnalyzeResult
}