package ru.muztache.feature.tuner.impl.di

import org.koin.dsl.module
import ru.muztache.feature.tuner.api.FeatureTunerApi
import ru.muztache.feature.tuner.impl.FeatureTunerImpl
import ru.muztache.feature.tuner.impl.data.di.dataModule
import ru.muztache.feature.tuner.impl.data.di.guitarQualifier
import ru.muztache.feature.tuner.impl.data.di.ukuleleQualifier
import ru.muztache.feature.tuner.impl.domain.di.domainModule
import ru.muztache.feature.tuner.impl.ui.di.uiModule

val featureTunerModule = module {
    includes(dataModule)
    includes(domainModule)
    includes(uiModule)
    factory<FeatureTunerApi> {
        FeatureTunerImpl(
            saveGuitarUseCase = get(guitarQualifier),
            saveUkuleleUseCase = get(ukuleleQualifier),
            getReservedGuitarsUseCase = get(guitarQualifier),
            getReservedUkulelesUseCase = get(ukuleleQualifier)
        )
    }
}
