package ru.muztache.feature.tuner.ui.entity.impl.guitar

import ru.muztache.feature.tuner.ui.engine.tone.Tone
import ru.muztache.feature.tuner.domain.entity.Tuning

abstract class GuitarTuning : Tuning() {

    abstract val firstString: Tone

    abstract val secondString: Tone

    abstract val thirdString: Tone

    abstract val fourthString: Tone

    abstract val fifthString: Tone

    abstract val sixthString: Tone

    override fun getNote(stringNumber: Int): Tone {
        return when (stringNumber) {
            0 -> firstString
            1 -> secondString
            2 -> thirdString
            3 -> fourthString
            4 -> fifthString
            5 -> sixthString
            else -> error("String number must be in range: [0, 6]")
        }
    }
}