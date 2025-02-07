package ru.muztache.feature.tuner.impl.ui.engine.processor

interface Processor {

    suspend fun start()

    suspend fun stop()
}
