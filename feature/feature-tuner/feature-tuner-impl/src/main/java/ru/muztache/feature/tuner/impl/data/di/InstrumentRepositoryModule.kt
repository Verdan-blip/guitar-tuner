package ru.muztache.feature.tuner.impl.data.di

import org.koin.dsl.module
import ru.muztache.feature.tuner.api.domain.entity.instrument.Guitar
import ru.muztache.feature.tuner.api.domain.entity.instrument.Ukulele
import ru.muztache.feature.tuner.api.domain.repository.ReservedInstrumentRepository
import ru.muztache.feature.tuner.api.domain.repository.TunedInstrumentRepository
import ru.muztache.feature.tuner.impl.data.repository.reserved.ReservedGuitarRepositoryImpl
import ru.muztache.feature.tuner.impl.data.repository.reserved.ReservedUkuleleRepositoryImpl
import ru.muztache.feature.tuner.impl.data.repository.tuned.TunedGuitarRepositoryImpl
import ru.muztache.feature.tuner.impl.data.repository.tuned.TunedUkulelesRepositoryImpl

internal val instrumentRepositoryModule = module {
    factory<ReservedInstrumentRepository<Guitar>>(guitarQualifier) {
        ReservedGuitarRepositoryImpl()
    }
    factory<ReservedInstrumentRepository<Ukulele>>(ukuleleQualifier) {
        ReservedUkuleleRepositoryImpl()
    }
    factory<TunedInstrumentRepository<Guitar>>(guitarQualifier) {
        TunedGuitarRepositoryImpl(get())
    }
    factory<TunedInstrumentRepository<Ukulele>>(ukuleleQualifier) {
        TunedUkulelesRepositoryImpl(get())
    }
}
