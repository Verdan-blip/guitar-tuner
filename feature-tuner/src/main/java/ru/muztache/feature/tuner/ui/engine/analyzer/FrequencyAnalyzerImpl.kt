package ru.muztache.feature.tuner.ui.engine.analyzer

import ru.muztache.feature.tuner.ui.engine.tone.Tone
import ru.muztache.feature.tuner.ui.entity.math.Deviation
import ru.muztache.feature.tuner.ui.entity.math.compareTo
import kotlin.math.abs

class FrequencyAnalyzerImpl : FrequencyAnalyzer {

    override fun analyze(frequency: Float): AnalyzeResult {
        var nearestTone: Tone? = null
        var minDiff: Float = Float.MAX_VALUE

        for (entry in Tone.entries) {
            val diff = abs(frequency - entry.frequency)
            if (diff < minDiff) {
                minDiff = diff
                nearestTone = entry
            }
        }

        return if (nearestTone != null) {
            analyzeComparing(frequency, nearestTone)
        } else {
            AnalyzeResult.Failure(frequency)
        }
    }

    override fun analyzeComparing(frequency: Float, tone: Tone): AnalyzeResult {
        val deviation = Deviation(frequency, tone.frequency)
        return AnalyzeResult.Success(
            nearestTone = tone,
            deviation = deviation,
            isTuned = deviation > ACCEPTABLE_DEVIATION_MIN_VALUE
                    && deviation < ACCEPTABLE_DEVIATION_MAX_VALUE,
            frequency = frequency
        )
    }

    companion object {

        private val ACCEPTABLE_DEVIATION_MIN_VALUE = Deviation(-2f)
        private val ACCEPTABLE_DEVIATION_MAX_VALUE = Deviation(2f)
    }
}