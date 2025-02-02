package ru.muztache.feature.tuner.api.domain.entity.instrument

import ru.muztache.feature.tuner.api.domain.entity.tuning.GuitarTuning

class Guitar(tuning: GuitarTuning) : StringInstrument<GuitarTuning>(6, tuning)