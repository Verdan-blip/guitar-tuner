package ru.muztache.feature.tuner.ui.engine.analyzer

import ru.muztache.feature.tuner.domain.entity.tone.ToneWithOctave
import ru.muztache.feature.tuner.domain.entity.tuning.Tuning

interface FrequencyAnalyzer {

    fun analyze(frequency: Float, tuning: Tuning): AnalyzeResult

    fun analyzeComparing(frequency: Float, toneToCompare: ToneWithOctave): AnalyzeResult
}