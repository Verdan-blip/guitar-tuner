package ru.muztache.guitartuner

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ru.muztache.core.common.di.commonModule
import ru.muztache.feature.splash.di.featureSplashModule
import ru.muztache.feature.tuner.impl.di.featureTunerModule
import ru.muztache.guitartuner.di.appModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            androidLogger()
            modules(appModule)
            modules(commonModule)
            modules(featureTunerModule)
            modules(featureSplashModule)
        }
    }
}