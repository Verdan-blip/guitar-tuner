package ru.muztache.feature.tuner.ui.engine.processor.audio

class AudioProcessedData(
    val pitch: Float,
    val isSilence: Boolean,
    val isPitched: Boolean,
    val probability: Float,
    val dBSPL: Double
)