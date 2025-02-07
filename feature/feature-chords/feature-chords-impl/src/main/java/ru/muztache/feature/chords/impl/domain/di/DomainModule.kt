package ru.muztache.feature.chords.impl.domain.di

import org.koin.dsl.module
import ru.muztache.core.common.di.iODispatcherQualifier
import ru.muztache.feature.chords.api.domain.usecase.GetAdvancedChordsUseCase
import ru.muztache.feature.chords.api.domain.usecase.GetBasicChordsUseCase
import ru.muztache.feature.chords.api.domain.usecase.GetReservedGuitarChordsUseCase
import ru.muztache.feature.chords.api.domain.usecase.SaveGuitarChordUseCase
import ru.muztache.feature.chords.impl.domain.usecase.GetAdvancedChordsUseCaseImpl
import ru.muztache.feature.chords.impl.domain.usecase.GetBasicChordsUseCaseImpl
import ru.muztache.feature.chords.impl.domain.usecase.GetReservedGuitarChordsUseCaseImpl
import ru.muztache.feature.chords.impl.domain.usecase.SaveGuitarChordUseCaseImpl

internal val domainModule = module {
    factory<GetReservedGuitarChordsUseCase> {
        GetReservedGuitarChordsUseCaseImpl(get(), get(iODispatcherQualifier))
    }
    factory<SaveGuitarChordUseCase> {
        SaveGuitarChordUseCaseImpl(get(), get(iODispatcherQualifier))
    }
    factory<GetBasicChordsUseCase> {
        GetBasicChordsUseCaseImpl(get(), get(iODispatcherQualifier))
    }
    factory<GetAdvancedChordsUseCase> {
        GetAdvancedChordsUseCaseImpl(get(), get(iODispatcherQualifier))
    }
}
