package ru.muztache.feature.tuner.ui.engine.analyzer

import ru.muztache.feature.tuner.domain.entity.tone.Tone
import ru.muztache.feature.tuner.domain.entity.tuning.Tuning

interface FrequencyAnalyzer {

    fun analyze(frequency: Float, tuning: Tuning): AnalyzeResult

    fun analyzeComparing(frequency: Float, tone: Tone): AnalyzeResult
}