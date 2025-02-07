package ru.muztache.feature.tuner.impl.ui.mvi

import ru.muztache.core.common.base.mvi.BaseState
import ru.muztache.feature.tuner.api.domain.entity.instrument.Guitar
import ru.muztache.feature.tuner.api.domain.entity.instrument.StringInstrument
import ru.muztache.feature.tuner.api.domain.entity.tone.Tone
import ru.muztache.feature.tuner.api.domain.entity.tone.ToneWithOctave
import ru.muztache.feature.tuner.api.domain.entity.tuning.GuitarTuning
import ru.muztache.feature.tuner.api.domain.entity.tuning.Tuning

internal data class State(
    val isEnabled: Boolean,
    val isAutoDetect: Boolean,
    val selectedInstrument: StringInstrument<out Tuning>,
    val idolNote: ToneWithOctave,
    val currentFrequency: Float,
    val selectedString: Int,
    val isTuned: Boolean,
    val shouldRequestPermission: Boolean
) : BaseState {

    companion object {

        fun create(): State {
            val instrument = Guitar(
                GuitarTuning(
                    string1 = ToneWithOctave(Tone.E, 1),
                    string2 = ToneWithOctave(Tone.E, 1),
                    string3 = ToneWithOctave(Tone.E, 1),
                    string4 = ToneWithOctave(Tone.E, 1),
                    string5 = ToneWithOctave(Tone.E, 1),
                    string6 = ToneWithOctave(Tone.E, 1),
                )
            )
            val currentString = 0
            return State(
                isEnabled = false,
                isAutoDetect = false,
                selectedInstrument = instrument,
                idolNote = instrument.getToneWithOctave(currentString),
                selectedString = currentString,
                currentFrequency = 0f,
                isTuned = false,
                shouldRequestPermission = false
            )
        }
    }
}
