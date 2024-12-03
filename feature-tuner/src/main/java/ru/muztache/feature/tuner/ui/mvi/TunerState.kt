package ru.muztache.feature.tuner.ui.mvi

import ru.muztache.core.common.base.BaseState
import ru.muztache.feature.tuner.ui.engine.tone.Tone
import ru.muztache.feature.tuner.ui.entity.impl.guitar.Guitar
import ru.muztache.feature.tuner.domain.entity.StringInstrument

data class TunerState(
    val isEnabled: Boolean,
    val isAutoDetect: Boolean,
    val selectedInstrument: StringInstrument,
    val idolNote: Tone,
    val currentFrequency: Float,
    val selectedString: Int,
    val isTuned: Boolean
) : BaseState {

    companion object {

        fun create(): TunerState {
            val instrument = Guitar()
            val currentString = 0
            return TunerState(
                isEnabled = false,
                isAutoDetect = false,
                selectedInstrument = instrument,
                idolNote = instrument.getTone(currentString),
                selectedString = currentString,
                currentFrequency = 0f,
                isTuned = false
            )
        }
    }
}