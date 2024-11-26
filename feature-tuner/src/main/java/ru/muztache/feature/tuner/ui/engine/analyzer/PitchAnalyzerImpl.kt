package ru.muztache.feature.tuner.ui.engine.analyzer

import ru.muztache.feature.tuner.ui.engine.mapper.ROOT_B_PITCH
import ru.muztache.feature.tuner.ui.engine.tone.Tone
import kotlin.math.abs

class PitchAnalyzerImpl : PitchAnalyzer {

    override fun analyze(pitch: Float): AnalyzeResult {
        var octave = 0
        var rootPitch = pitch
        while (rootPitch > ROOT_B_PITCH) { rootPitch /= 2; octave++ }

        var closestTone: Tone? = null
        var minDiff: Float = Float.MAX_VALUE

        for (entry in Tone.entries) {
            val diff = abs(rootPitch - entry.frequency)
            if (diff < minDiff) {
                minDiff = diff
                closestTone = entry
            }
        }

        return if (closestTone != null) {
            AnalyzeResult.Success(closestTone, minDiff, pitch)
        } else {
            AnalyzeResult.Failure(pitch)
        }
    }

    override fun calculateDiff(pitch: Float, tone: Tone): Float {
        return tone.frequency - pitch
    }
}