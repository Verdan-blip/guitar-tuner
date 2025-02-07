package ru.muztache.feature.chords.impl.ui.screens.details.mvi

import ru.muztache.core.common.base.mvi.BaseState
import ru.muztache.core.common.entity.FetchRequest

internal data class ChordDetailsState(
    val chords: FetchRequest = FetchRequest.Pending
) : BaseState
