package ru.muztache.feature.tuner.domain.entity.instrument

import ru.muztache.feature.tuner.domain.entity.tuning.GuitarTuning

class Guitar(tuning: GuitarTuning) : StringInstrument<GuitarTuning>(6, tuning)