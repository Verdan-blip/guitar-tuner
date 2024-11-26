package ru.muztache.feature.tuner.ui.engine.processor

interface Processor {

    suspend fun start()

    suspend fun stop()
}