package ru.muztache.feature.tuner.impl.ui.engine.analyzer

import ru.muztache.feature.tuner.api.domain.entity.tone.ToneWithOctave
import ru.muztache.feature.tuner.api.domain.entity.tuning.Tuning
import ru.muztache.feature.tuner.api.domain.entity.tuning.forEachTone
import ru.muztache.feature.tuner.impl.ui.entity.math.Deviation
import ru.muztache.feature.tuner.impl.ui.entity.math.compareTo

class FrequencyAnalyzerImpl : FrequencyAnalyzer {

    override fun analyze(frequency: Float, tuning: Tuning): AnalyzeResult {

        var nearestTone = tuning.getNote(0)
        var minDiff = frequency - nearestTone.frequency

        tuning.forEachTone(skipIndex = 0) { toneWithOctave ->
            val diff = frequency - toneWithOctave.frequency
            if (diff < minDiff) {
                minDiff = diff
                nearestTone = toneWithOctave
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

    override fun analyzeComparing(
        frequency: Float,
        toneToCompare: ToneWithOctave
    ): AnalyzeResult {
        val deviation = Deviation(frequency, toneToCompare.frequency)
        return AnalyzeResult.Success(
            nearestTone = toneToCompare,
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