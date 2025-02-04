package ru.muztache.core.data.di

import org.koin.dsl.module
import ru.muztache.core.data.local.di.localModule

val dataModule = module {
    includes(localModule)
}