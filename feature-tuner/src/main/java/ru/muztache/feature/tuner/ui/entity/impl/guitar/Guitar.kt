package ru.muztache.feature.tuner.ui.entity.impl.guitar

import ru.muztache.feature.tuner.domain.entity.StringInstrument
import ru.muztache.feature.tuner.domain.entity.Tuning

class Guitar : StringInstrument() {

    override val stringCount: Int = 6

    override var tuning: Tuning = GuitarStandardTuning
}