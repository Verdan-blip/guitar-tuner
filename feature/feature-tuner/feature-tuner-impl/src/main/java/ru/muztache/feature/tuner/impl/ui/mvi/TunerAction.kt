package ru.muztache.feature.tuner.impl.ui.mvi

import ru.muztache.feature.tuner.impl.ui.entity.math.Deviation

sealed interface TunerAction {

    class OnNewDeviation(val deviation: Deviation) : TunerAction
}