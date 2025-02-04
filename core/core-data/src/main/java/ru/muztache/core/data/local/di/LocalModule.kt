package ru.muztache.core.data.local.di

import org.koin.dsl.module
import ru.muztache.core.data.local.auth.di.authModule

internal val localModule = module {
    includes(authModule)
}