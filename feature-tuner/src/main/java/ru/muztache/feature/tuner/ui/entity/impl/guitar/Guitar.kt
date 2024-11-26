package ru.muztache.feature.tuner.ui.entity.impl.guitar

import ru.muztache.feature.tuner.ui.entity.instrument.StringInstrument
import ru.muztache.feature.tuner.ui.entity.tuning.Tuning

class Guitar : StringInstrument() {

    override val stringCount: Int = 6

    override var tuning: Tuning = GuitarStandardTuning
}