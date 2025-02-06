package ru.muztache.feature.tuner.impl

import ru.muztache.feature.tuner.api.FeatureTunerApi
import ru.muztache.feature.tuner.api.domain.entity.instrument.Guitar
import ru.muztache.feature.tuner.api.domain.entity.instrument.Ukulele
import ru.muztache.feature.tuner.api.domain.usecase.get.GetReservedInstrumentsUseCase
import ru.muztache.feature.tuner.api.domain.usecase.save.SaveInstrumentUseCase

class FeatureTunerImpl(
    override val saveGuitarUseCase: SaveInstrumentUseCase<Guitar>,
    override val saveUkuleleUseCase: SaveInstrumentUseCase<Ukulele>,
    override val getReservedGuitarsUseCase: GetReservedInstrumentsUseCase<Guitar>,
    override val getReservedUkulelesUseCase: GetReservedInstrumentsUseCase<Ukulele>
) : FeatureTunerApi