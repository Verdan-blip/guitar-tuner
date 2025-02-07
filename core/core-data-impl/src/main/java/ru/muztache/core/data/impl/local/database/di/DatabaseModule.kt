package ru.muztache.core.data.impl.local.database.di

import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.koin.dsl.module
import ru.muztache.core.data.impl.GuitarTunerDatabase

internal val databaseModule = module {
    single {
        GuitarTunerDatabase(
            driver = AndroidSqliteDriver(
                schema = GuitarTunerDatabase.Schema,
                context = get(),
                name = "guitar_tuner_database"
            )
        )
    }
}
