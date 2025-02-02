package ru.muztache.feature.tuner.api.domain.entity.group

import ru.muztache.feature.tuner.api.domain.entity.instrument.StringInstrument

class StringInstrumentGroup<S : StringInstrument<*>>(
    val instruments: Map<String, S>
)