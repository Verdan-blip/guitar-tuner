package ru.muztache.feature.tuner.ui.engine.analyzer

import ru.muztache.feature.tuner.ui.engine.tone.Tone
import ru.muztache.feature.tuner.ui.entity.math.Deviation
import ru.muztache.feature.tuner.ui.entity.math.compareTo
import ru.muztache.feature.tuner.domain.entity.Tuning
import kotlin.math.abs

class FrequencyAnalyzerImpl : FrequencyAnalyzer {

    override fun analyze(frequency: Float, tuning: Tuning): AnalyzeResult {

        var nearestTone = tuning.getNote(0)
        var minDiff = frequency - nearestTone.frequency

        for (i in 1..tuning.stringsCount) {
            val suspectTone = tuning.getNote(i)
            val diff = abs(frequency - suspectTone.frequency)
            if (diff < minDiff) {
                minDiff = diff
                nearestTone = suspectTone
            }
        }

        val deviation = Deviation(frequency, nearestTone.frequency)
        return AnalyzeResult.Success(
            nearestTone = nearestTone,
            deviation = deviation,
            isTuned = deviation > ACCEPTABLE_DEVIATION_MIN_VALUE
                    && deviation < ACCEPTABLE_DEVIATION_MAX_VALUE,
            frequency = frequency
        )
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