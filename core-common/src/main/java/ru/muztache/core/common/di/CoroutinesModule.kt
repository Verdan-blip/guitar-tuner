package ru.muztache.core.common.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.muztache.core.common.coroutines.applicationScope

val iODispatcherQualifier = named("iODispatcher")
val mainDispatcherQualifier = named("mainDispatcher")
val defaultDispatcherQualifier = named("defaultDispatcher")

internal val coroutinesModule = module {
    //Scope
    single<CoroutineScope> { applicationScope }
    //Dispatchers
    single<CoroutineDispatcher>(iODispatcherQualifier) { Dispatchers.IO }
    single<CoroutineDispatcher>(mainDispatcherQualifier) { Dispatchers.Main }
    single<CoroutineDispatcher>(defaultDispatcherQualifier) { Dispatchers.Default }
}