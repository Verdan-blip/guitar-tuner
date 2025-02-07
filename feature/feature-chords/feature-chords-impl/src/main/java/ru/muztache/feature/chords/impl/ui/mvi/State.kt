package ru.muztache.feature.chords.impl.ui.mvi

import ru.muztache.core.common.base.mvi.BaseState
import ru.muztache.core.common.entity.FetchRequest

internal data class State(
    val baseChords: FetchRequest = FetchRequest.Pending
) : BaseState

