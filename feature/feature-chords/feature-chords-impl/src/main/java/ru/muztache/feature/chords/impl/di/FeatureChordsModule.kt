package ru.muztache.feature.chords.impl.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.muztache.feature.chords.api.FeatureChordsApi
import ru.muztache.feature.chords.impl.FeatureChordImpl
import ru.muztache.feature.chords.impl.data.di.dataModule
import ru.muztache.feature.chords.impl.domain.di.domainModule
import ru.muztache.feature.chords.impl.ui.di.uiModule

val featureChordsModule = module {
    includes(dataModule)
    includes(domainModule)
    includes(uiModule)
    factoryOf(::FeatureChordImpl) bind FeatureChordsApi::class
}