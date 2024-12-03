package ru.muztache.feature.tuner.ui.entity.impl.ukulele

import ru.muztache.feature.tuner.domain.entity.StringInstrument
import ru.muztache.feature.tuner.domain.entity.Tuning

class Ukulele : StringInstrument() {

    override val stringCount: Int = 4

    override var tuning: Tuning
        get() = TODO("Not yet implemented")
        set(value) {}
}