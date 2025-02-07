package ru.muztache.feature.tuner.impl.ui.mvi

import ru.muztache.feature.tuner.impl.ui.entity.Deviation

internal sealed interface Action {

    class OnNewDeviation(val deviation: Deviation) : Action
}
