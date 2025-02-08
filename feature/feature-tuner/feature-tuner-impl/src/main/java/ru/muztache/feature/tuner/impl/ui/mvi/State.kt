package ru.muztache.feature.tuner.impl.ui.mvi

import ru.muztache.core.common.base.mvi.BaseState
import ru.muztache.core.common.entity.FetchRequest
import ru.muztache.feature.tuner.api.domain.entity.instrument.StringInstrument
import ru.muztache.feature.tuner.api.domain.entity.tone.Tone
import ru.muztache.feature.tuner.api.domain.entity.tone.ToneWithOctave

internal data class State(
    val isEnabled: Boolean = true,
    val isAutoDetect: Boolean = false,
    val selectedKey: FetchRequest = FetchRequest.Pending,
    val savedTunings: Map<String, StringInstrument<*>> = mapOf(),
    val idolNote: ToneWithOctave = ToneWithOctave(Tone.UNRECOGNIZED, 0),
    val currentFrequency: Float = 0f,
    val selectedString: Int = 0,
    val isTuned: Boolean = false
) : BaseState
