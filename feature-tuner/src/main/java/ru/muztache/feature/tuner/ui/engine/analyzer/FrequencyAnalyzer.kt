package ru.muztache.feature.tuner.ui.engine.analyzer

import ru.muztache.feature.tuner.ui.engine.tone.Tone
import ru.muztache.feature.tuner.domain.entity.Tuning

interface FrequencyAnalyzer {

    fun analyze(frequency: Float, tuning: Tuning): AnalyzeResult

    fun analyzeComparing(frequency: Float, tone: Tone): AnalyzeResult
}