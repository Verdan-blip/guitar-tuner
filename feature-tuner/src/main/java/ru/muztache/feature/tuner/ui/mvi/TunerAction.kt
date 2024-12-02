package ru.muztache.feature.tuner.ui.mvi

import ru.muztache.feature.tuner.ui.entity.math.Deviation

sealed interface TunerAction {

    class OnNewDeviation(val deviation: Deviation) : TunerAction
}