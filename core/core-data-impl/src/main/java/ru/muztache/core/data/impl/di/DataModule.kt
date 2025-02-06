package ru.muztache.core.data.impl.di

import org.koin.dsl.module
import ru.muztache.core.data.impl.local.di.localModule

val dataModule = module {
    includes(localModule)
}