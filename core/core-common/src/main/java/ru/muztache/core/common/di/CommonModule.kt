package ru.muztache.core.common.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.muztache.core.common.provider.ResourceProvider
import ru.muztache.core.common.provider.ResourceProviderImpl

val commonModule = module {
    includes(coroutinesModule)
    factoryOf(::ResourceProviderImpl) bind ResourceProvider::class
}
