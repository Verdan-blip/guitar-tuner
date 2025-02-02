package ru.muztache.feature.tuner.impl.ui.mvi

import ru.muztache.core.common.base.mvi.BaseState
import ru.muztache.feature.tuner.api.domain.entity.instrument.Guitar
import ru.muztache.feature.tuner.api.domain.entity.instrument.StringInstrument
import ru.muztache.feature.tuner.api.domain.entity.tone.Tone
import ru.muztache.feature.tuner.api.domain.entity.tone.ToneWithOctave
import ru.muztache.feature.tuner.api.domain.entity.tuning.GuitarTuning
import ru.muztache.feature.tuner.api.domain.entity.tuning.Tuning

data class TunerState(
    val isEnabled: Boolean,
    val isAutoDetect: Boolean,
    val selectedInstrument: StringInstrument<out Tuning>,
    val idolNote: ToneWithOctave,
    val currentFrequency: Float,
    val selectedString: Int,
    val isTuned: Boolean
) : BaseState {

    companion object {

        fun create(): TunerState {
            val instrument = Guitar(
                GuitarTuning(
                    string1 = ToneWithOctave(Tone.E, 4),
                    string2 = ToneWithOctave(Tone.E, 4),
                    string3 = ToneWithOctave(Tone.E, 4),
                    string4 = ToneWithOctave(Tone.E, 4),
                    string5 = ToneWithOctave(Tone.E, 4),
                    string6 = ToneWithOctave(Tone.E, 4),
                )
            )
            val currentString = 0
            return TunerState(
                isEnabled = false,
                isAutoDetect = false,
                selectedInstrument = instrument,
                idolNote = instrument.getToneWithOctave(currentString),
                selectedString = currentString,
                currentFrequency = 0f,
                isTuned = false
            )
        }
    }
}