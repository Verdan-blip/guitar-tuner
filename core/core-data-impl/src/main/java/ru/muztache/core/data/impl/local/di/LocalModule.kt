package ru.muztache.core.data.impl.local.di

import org.koin.dsl.module
import ru.muztache.core.data.impl.local.auth.di.authModule
import ru.muztache.core.data.impl.local.chords.di.chordsModule
import ru.muztache.core.data.impl.local.database.di.databaseModule
import ru.muztache.core.data.impl.local.user.di.userModule

internal val localModule = module {
    includes(databaseModule)
    includes(authModule)
    includes(chordsModule)
    includes(userModule)
}
