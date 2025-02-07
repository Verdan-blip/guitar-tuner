package ru.muztache.feature.tuner.impl.domain.di

import org.koin.dsl.module
import ru.muztache.core.common.di.iODispatcherQualifier
import ru.muztache.feature.tuner.api.domain.entity.instrument.Guitar
import ru.muztache.feature.tuner.api.domain.entity.instrument.Ukulele
import ru.muztache.feature.tuner.impl.domain.usecase.get.GetReservedGuitarsUseCase
import ru.muztache.feature.tuner.api.domain.usecase.get.GetReservedInstrumentsUseCase
import ru.muztache.feature.tuner.impl.domain.usecase.get.GetReservedUkulelesUseCase
import ru.muztache.feature.tuner.impl.domain.usecase.get.GetTunedGuitarsUseCase
import ru.muztache.feature.tuner.api.domain.usecase.get.GetTunedInstrumentsUseCase
import ru.muztache.feature.tuner.impl.domain.usecase.get.GetTunedUkulelesUseCase
import ru.muztache.feature.tuner.impl.domain.usecase.save.SaveGuitarUseCase
import ru.muztache.feature.tuner.api.domain.usecase.save.SaveInstrumentUseCase
import ru.muztache.feature.tuner.impl.domain.usecase.save.SaveUkuleleUseCase
import ru.muztache.feature.tuner.impl.data.di.guitarQualifier
import ru.muztache.feature.tuner.impl.data.di.ukuleleQualifier

internal val domainModule = module {
    factory<GetReservedInstrumentsUseCase<Guitar>>(guitarQualifier) {
        GetReservedGuitarsUseCase(get(guitarQualifier), get(iODispatcherQualifier))
    }
    factory<GetReservedInstrumentsUseCase<Ukulele>>(ukuleleQualifier) {
        GetReservedUkulelesUseCase(get(ukuleleQualifier), get(iODispatcherQualifier))
    }
    factory<GetTunedInstrumentsUseCase<Guitar>>(guitarQualifier) {
        GetTunedGuitarsUseCase(get(guitarQualifier), get(iODispatcherQualifier))
    }
    factory<GetTunedInstrumentsUseCase<Ukulele>>(ukuleleQualifier) {
        GetTunedUkulelesUseCase(get(ukuleleQualifier), get(iODispatcherQualifier))
    }
    factory<SaveInstrumentUseCase<Guitar>>(guitarQualifier) {
        SaveGuitarUseCase(get(guitarQualifier), get(iODispatcherQualifier))
    }
    factory<SaveInstrumentUseCase<Ukulele>>(ukuleleQualifier) {
        SaveUkuleleUseCase(get(ukuleleQualifier), get(iODispatcherQualifier))
    }
}
