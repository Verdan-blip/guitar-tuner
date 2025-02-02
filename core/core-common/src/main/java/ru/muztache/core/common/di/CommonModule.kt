package ru.muztache.core.common.di

import org.koin.dsl.module

val commonModule = module {
    includes(coroutinesModule)
}