package ru.muztache.core.common.di

import kotlinx.coroutines.CoroutineScope
import org.koin.dsl.module
import ru.muztache.core.common.coroutines.applicationScope

internal val coroutinesScopes = module {
    single<CoroutineScope> { applicationScope }
}