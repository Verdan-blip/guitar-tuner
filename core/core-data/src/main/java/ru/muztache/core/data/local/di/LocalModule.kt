package ru.muztache.core.data.local.di

import org.koin.dsl.module
import ru.muztache.core.data.local.auth.di.authModule
import ru.muztache.core.data.local.chords.di.chordsModule

internal val localModule = module {
    includes(authModule)
    includes(chordsModule)
}