package ru.muztache.feature.tuner.api

import ru.muztache.feature.tuner.api.domain.entity.instrument.Guitar
import ru.muztache.feature.tuner.api.domain.entity.instrument.Ukulele
import ru.muztache.feature.tuner.api.domain.usecase.get.reserved.GetReservedInstrumentsUseCase
import ru.muztache.feature.tuner.api.domain.usecase.save.SaveInstrumentUseCase

interface FeatureTunerApi {
    val saveGuitarUseCase: SaveInstrumentUseCase<Guitar>
    val saveUkuleleUseCase: SaveInstrumentUseCase<Ukulele>
    val getReservedGuitarsUseCase: GetReservedInstrumentsUseCase<Guitar>
    val getReservedUkulelesUseCase: GetReservedInstrumentsUseCase<Ukulele>
}