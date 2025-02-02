package ru.muztache.feature.tuner.api.domain.entity.instrument

import ru.muztache.feature.tuner.api.domain.entity.tuning.UkuleleTuning

class Ukulele(tuning: UkuleleTuning) : StringInstrument<UkuleleTuning>(4, tuning)